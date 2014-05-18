name := "simpleCRM"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.google.guava" % "guava" % "r05",
  "ws.securesocial" %% "securesocial" % "2.1.3",
  filters
)     

play.Project.playJavaSettings
