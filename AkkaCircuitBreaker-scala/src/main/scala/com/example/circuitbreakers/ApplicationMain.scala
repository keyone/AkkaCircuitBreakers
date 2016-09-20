package com.example.circuitbreakers

import scala.concurrent.Await

import akka.actor.ActorSystem
import scala.concurrent.duration._
import scala.io.StdIn

object ApplicationMain extends App {

  // Create the system and service
  val system = ActorSystem("MySystem")
  val service = system.actorOf(ServiceActor.props, "System")

  // Create the user actors
  val userCount = 10
  (1 to userCount).foreach(createUser)

  // Let this run until the user wishes to stop
  println("System running, press enter to shutdown")
  StdIn.readLine()

  // We're done, shutdown
  Await.result(system.terminate(), 3 seconds)


  private def createUser(i: Int): Unit = {
    import system.dispatcher
    system.scheduler.scheduleOnce(i seconds) {
      system.actorOf(UserActor.props(service), s"User$i")
    }
  }
}
