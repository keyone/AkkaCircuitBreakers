name := """AkkaCircuitBreaker-scala"""

version := "1.0"

scalaVersion := "2.11.7"

val akkaVer = "2.4.0"
val logbackVer = "1.1.3"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"      % akkaVer,
  "com.typesafe.akka" %% "akka-testkit"    % akkaVer % "test",
  "com.typesafe.akka" %% "akka-slf4j"      % akkaVer,
  "ch.qos.logback"    %  "logback-classic" % logbackVer,
  "org.scalatest"     %% "scalatest"       % "2.2.4" % "test")

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8",
  "-Xcheckinit"
)
