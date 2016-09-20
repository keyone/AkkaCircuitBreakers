package com.example.circuitbreakers

import akka.actor.ActorLogging
import Service._

object Service {
  case object Request
  case object Response
}

trait Service {
  self: ActorLogging =>

  // Keep a count of requests
  private var requestCount = 0

  // Max count and delays
  private val maxReqCount = 10
  private val normalDelay = 100
  private val restartDelay = 3100  // Exercise: Test with < 3000 and > 3000

  // TODO have other actor restart itself after a delay
  protected def callWebService(): Response.type = {

    if(requestCount < maxReqCount) {
      requestCount += 1
      Thread.sleep(normalDelay)
    } else {
      // Service shuts down, takes a while to come back up
      log.error("!! Service overloaded !!")
      Thread.sleep(restartDelay)
      requestCount = 0
    }

    Response
  }
}


