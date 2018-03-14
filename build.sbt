name := "GeoStream"
scalaVersion := "2.11.12"
version := "1.1"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

libraryDependencies ++= Seq(
    "com.moandjiezana.toml" % "toml4j" % "0.4.0",
    "org.atilika.kuromoji" % "kuromoji" % "0.7.7",
    "org.apache.spark" % "spark-streaming_2.11" % "2.2.0",
    "org.apache.spark" %% "spark-streaming-twitter" % "1.6.3"
)
