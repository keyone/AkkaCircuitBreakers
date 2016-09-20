package com.example.circuitbreakers

import akka.actor.ActorLogging
import Service._
import scala.util.Random

object Service {
  case object Request
  case object Response
}

trait Service {
  self: ActorLogging =>

  // Max count and delays
  private val normalDelay = 100
  private val restartDelay = 3100  // Exercise: Test with < 3000 and > 3000

  protected def callWebService(): Response.type = {

    if(Random.nextDouble() >= 0.1) {
      Thread.sleep(normalDelay)
    } else {
      // Service shuts down, takes a while to come back up
      log.error("!! Service overloaded !! Restarting !!")
      Thread.sleep(restartDelay)
    }

    Response
  }
}


