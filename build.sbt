name := "scala-learnings"
organization := "com.github.kpbochenek"
description := "Scala various learning snippets"
version := "0.1"

scalaVersion := "2.13.3"

lazy val global = project
  .in(file("."))
  .settings()
  .aggregate(apachekafka)

lazy val apachekafka = project
  .in(file("apachekafka"))
  .settings(
    name := "apachekafka",
    libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.6.0",
    //libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
    libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.11.2"
  )
