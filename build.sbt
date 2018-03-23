name := "GeoStream"

scalaVersion := "2.11.10"
version := "1.1"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")

resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

libraryDependencies ++= Seq(
    "com.typesafe" % "config" % "1.3.2",
    "org.atilika.kuromoji" % "kuromoji" % "0.7.7",
    "org.apache.spark" %% "spark-core" % "2.2.0",
    "org.apache.spark" %% "spark-streaming" % "2.2.0" % "provided",
    "org.apache.bahir" %% "spark-streaming-twitter" % "2.2.0",
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Xfuture",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-unused-import",
  "-Ywarn-value-discard"
)
