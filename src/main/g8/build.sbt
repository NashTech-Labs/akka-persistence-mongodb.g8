name := "akka-persistence-mongodb"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= {
  val akkaVersion = "2.4.12"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "com.github.ironfish" %% "akka-persistence-mongo"  % "1.0.0-SNAPSHOT",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" % "scalatest_2.11" % "3.0.1" % "test",
    "com.github.dnvriend" %% "akka-persistence-inmemory" % "1.3.14" % "test"
  )
}

mainClass in (Compile, run) := Some("com.knoldus.main.Launcher")