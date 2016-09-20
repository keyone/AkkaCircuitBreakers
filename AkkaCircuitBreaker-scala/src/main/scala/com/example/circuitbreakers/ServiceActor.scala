package com.example.circuitbreakers

import akka.actor.{Actor, ActorLogging, Props}
import com.example.circuitbreakers.ServiceActor._

object ServiceActor {

  case object Request
  case object Response

  def props: Props =
    Props(new ServiceActor)

}

class ServiceActor() extends Actor with ActorLogging {

  // Keep a count of requests
  private var requestCount = 0

  // Max count and delays
  private val maxReqCount = 10
  private val normalDelay = 100
  private val restartDelay = 3100  // Exercise: Test with < 3000 and > 3000


  override def receive: Receive = {
    case Request =>
      callWebService()
      sender() ! Response
  }

  private def callWebService(): Unit = {

    if(requestCount < maxReqCount) {
      requestCount += 1
      Thread.sleep(normalDelay)
    } else {
      // Service shuts down, takes a while to come back up
      log.error("!! Service crashed !! restarting...")
      Thread.sleep(restartDelay)
      requestCount = 0
    }
  }

}
