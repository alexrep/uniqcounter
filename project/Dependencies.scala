import sbt._

object Dependencies {
  lazy val akkaVersion = "2.6.10"
  lazy val akkaHttpVersion = "10.2.1"


  lazy val scalatest =  "org.scalatest" %% "scalatest" % "3.2.0"

  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val spray = "com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion


  lazy val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion
  lazy val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
  lazy val specs2Core = "org.specs2" %% "specs2-core" % "4.5.1"
  lazy val guava = "com.google.guava" % "guava" % "22.0"
}
