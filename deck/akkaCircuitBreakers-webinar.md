autoscale: true
build-lists: true

![](images/working.jpg)

![inline 50%](images/yp.logo.white.png)

# Akka Circuit Breakers

## Alejandro Lujan

---

![](images/working.jpg)

# Who Am I?

- Typesafe => Lightbend fan for ~6 years
  - Developer, Trainer, Mentor
  - Scala, Akka, Play, *Lagom*
- ![inline 35%](images/sun.png) Scala Up North organizer 
- Java dev / architect for 10 years
- Other stuff: C#, C++, <sub>PHP</sub>

---

![](images/working.jpg)

# What we'll cover

- The problem with timeouts
- Circuit breaker basics
- Code samples

---

# A code example

For readability, code slides won't have an image background

```scala
class ServiceActor() extends Actor with ActorLogging {

  override def receive: Receive = {
    case message =>
      log.info("Received this message: '{}', responding", message)
      sender() ! "response"
  }

}
```

---

![](images/working.jpg)

# [fit] Thanks!

## Upcoming webinars:

- **October 21**: Akka Typed - Type Sanity for Your Actors

- **November 29**: CRDTs and Akka Distributed Data

## @Yopp_Works
## yoppworks.com/events
## info@yoppworks.com