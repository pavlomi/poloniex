name := "poloniex"

organization := "pavlomi"

version := "0.0.1"

scalaVersion := "2.12.6"

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= {
  val akkaV = "2.4.18"
  val akkaHttpV = "10.0.4"
  val scalaTestV = "3.0.1"
  val scalaMockV = "3.5.0"
  val enumeratumV = "1.5.12"
  val kebsV = "1.4.1"
  val bcryptV = "3.0"

  lazy val akkaBase = Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-slf4j" % akkaV
  )

  lazy val akkaHttp = Seq(
    "com.typesafe.akka" %% "akka-http-core" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV % "test"
  )

  lazy val others = Seq(
    "com.beachape" %% "enumeratum" % enumeratumV,
    "pl.iterators" %% "kebs-spray-json" % kebsV,
    "com.github.t3hnar" %% "scala-bcrypt" % bcryptV
  )
  akkaBase ++ akkaHttp ++ others
}
