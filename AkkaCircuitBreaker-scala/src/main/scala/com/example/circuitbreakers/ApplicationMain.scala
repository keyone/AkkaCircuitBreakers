package com.example.circuitbreakers

import scala.concurrent.Await

import akka.actor.ActorSystem
import scala.concurrent.duration._
import scala.io.StdIn

object ApplicationMain extends App {

  val system = ActorSystem("MySystem")
  val service = system.actorOf(ServiceActor.props)

  val userCount = 5
  (1 to userCount).foreach(
    i => system.actorOf(UserActor.props(service), s"User$i")
  )


  println("System running, press enter to shutdown")
  StdIn.readLine()

  // We're done, shutdown
  Await.result(system.terminate(), 3 seconds)
}
