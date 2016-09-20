package com.example.circuitbreakers

import scala.concurrent.Await

import akka.actor.ActorSystem
import scala.concurrent.duration._
import com.example.PingActor
import scala.io.StdIn

object ApplicationMain extends App {
  val system = ActorSystem("MySystem")

  val service = system.actorOf(ServiceActor.props)
  val user = system.actorOf(UserActor.props(service))

  println("System running, press a key to shutdown")
  StdIn.readLine()

  // We're done, shutdown
  Await.result(system.terminate(), 3 seconds)
}
