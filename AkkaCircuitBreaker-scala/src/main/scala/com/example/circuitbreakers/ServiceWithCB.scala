package com.example.circuitbreakers

import java.util.concurrent.TimeoutException

import scala.concurrent.Future
import scala.concurrent.duration._
import akka.pattern.CircuitBreaker
import akka.pattern.pipe
import akka.actor.{Props, Actor, ActorLogging}
import Service._

object ServiceWithCB {
  def props: Props =
    Props(new ServiceWithCB)
}

class ServiceWithCB extends Actor with ActorLogging with Service {

  import context.dispatcher

  val breaker =
    new CircuitBreaker(
      context.system.scheduler,
      maxFailures = 1,
      callTimeout = 2 seconds,
      resetTimeout = 10 seconds).
        onOpen(notifyMe("Open")).
        onClose(notifyMe("Closed")).
        onHalfOpen(notifyMe("Half Open"))

  private def notifyMe(state: String): Unit =
    log.warning(s"My CircuitBreaker is now $state")

  override def receive: Receive = {
    case Request =>
      breaker.withCircuitBreaker(Future(callWebService())) pipeTo sender()
  }

}
