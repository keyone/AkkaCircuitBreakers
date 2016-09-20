package com.example.circuitbreakers

import akka.actor.Actor.Receive
import akka.actor.{Props, ActorLogging, Actor}

object ServiceActor {

  def props: Props =
    Props(new ServiceActor)

}

class ServiceActor() extends Actor with ActorLogging {

  override def receive: Receive = {
    case message =>
      log.info("Received this message: '{}', responding", message)
      sender() ! "response"
  }

}
