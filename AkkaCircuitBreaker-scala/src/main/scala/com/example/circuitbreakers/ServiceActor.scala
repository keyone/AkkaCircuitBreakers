package com.example.circuitbreakers

import akka.actor.Actor.Receive
import akka.actor.{Props, ActorLogging, Actor}
import ServiceActor._

object ServiceActor {

  case object Request
  case object Response

  def props: Props =
    Props(new ServiceActor)

}

class ServiceActor() extends Actor with ActorLogging {

  override def receive: Receive = {
    case message =>
      // not responding right now
      sender() ! Response
  }

}
