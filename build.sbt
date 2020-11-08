import Dependencies._

classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.AllLibraryJars
name := "counter"

ThisBuild / scalaVersion := "2.12.10"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

libraryDependencies ++= Seq(
  akkaHttp,
  akkaStream,
  specs2Core % Test,
  spray,
  guava,
  scalatest
)

assemblyJarName in assembly := name.value + ".jar"

assemblyMergeStrategy in assembly := {
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}