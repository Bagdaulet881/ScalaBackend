name := "akka-persistence"

version := "0.1"

scalaVersion := "2.13.4"

val AkkaVersion = "2.6.10"
val AkkaHttpVersion = "10.2.1"
val circeVersion = "0.13.0"

libraryDependencies ++= Seq(
  //test akka persistence
  "com.typesafe.akka" %% "akka-persistence-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-serialization-jackson" % AkkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
  "org.scalatest" %% "scalatest" % "3.1.0" % Test,

//  for http
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,

  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,

  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "de.heikoseeberger" %% "akka-http-circe" % "1.31.0"
)

//scalacOptions in Compile ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint")

// show full stack traces and test case durations
//testOptions in Test += Tests.Argument("-oDF")
//logBuffered in Test := false

//licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))
