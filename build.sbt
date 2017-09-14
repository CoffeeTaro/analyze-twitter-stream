name := "kuromoji-scala"
scalaVersion := "2.12.3"
version := "1.0"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"
libraryDependencies ++= Seq(
    "org.atilika.kuromoji" % "kuromoji" % "0.7.7"
)
