package com.example.circuitbreakers

import akka.actor.{ActorRef, ActorLogging, Actor, Props}


object UserActor {

  def props(service: ActorRef): Props =
    Props(new UserActor(service))

}

class UserActor(service: ActorRef) extends Actor with ActorLogging {

  service ! "Sending a request"


  override def receive: Receive = {
    case message => log.info("Received this message: '{}'", message)
  }

}
