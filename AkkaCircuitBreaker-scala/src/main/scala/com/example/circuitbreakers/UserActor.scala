package com.example.circuitbreakers

import akka.actor.{ActorRef, ActorLogging, Actor, Props}
import scala.concurrent.duration._
import akka.pattern.ask
import akka.pattern.pipe
import akka.util.Timeout
import akka.actor.Status.Failure
import akka.pattern.AskTimeoutException

object UserActor {

  def props(service: ActorRef): Props =
    Props(new UserActor(service))

}

class UserActor(service: ActorRef) extends Actor with ActorLogging {

  import context.dispatcher

  // Send our first request
  sendRequest

  override def receive: Receive = {
    case ServiceActor.Response =>
      log.info("Got a quick response, I'm a happy actor")
      sendRequest

    case Failure(ex: AskTimeoutException) =>
      log.error("Got bored of waiting, I'm outta here!")
      context.stop(self)

  }

  private def sendRequest = {

    implicit val timeout = Timeout(5 seconds)

    // Send a message, pipe response to ourselves
    context.system.scheduler.scheduleOnce(1 second) {
      val response = service ? ServiceActor.Request
      response.pipeTo(self)
    }
  }

}
