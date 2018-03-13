name := "GeoStream"
scalaVersion := "2.11.12"
version := "1.1"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"
libraryDependencies ++= Seq(
    "org.atilika.kuromoji" % "kuromoji" % "0.7.7"
)
