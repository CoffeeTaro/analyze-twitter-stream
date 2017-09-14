name := "hello"
scalaVersion := "2.12.3"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"
libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "2.1.0"
    "org.atilika.kuromoji" % "kuromoji" % "0.7.7"
)
