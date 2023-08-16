ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "sbt-test",
    libraryDependencies ++= Seq(
      //  a server infrastructure library
      "com.twitter" %% "twitter-server" % "22.12.0",

      // an incarnation of an HTTP server library for the above infrastructure
      "com.github.finagle" %% "finch-core" % "0.34.1",
      // with ability to automatically encode/decode JSON payloads via the circe library below
      "com.github.finagle" %% "finch-circe" % "0.34.1",
      "com.github.finagle" %% "finch-generic" % "0.34.1",

      // and with testing abilities
      "com.github.finagle" %% "finch-test" % "0.34.1" % "test",
      "com.github.finagle" %% "finch-json-test" % "0.34.1" % "test",

      "org.scalatest" %% "scalatest" % "3.2.3" % "test",

      // io.circe is a JSON library
      "io.circe" %% "circe-core" % "0.14.5",
      "io.circe" %% "circe-parser" % "0.14.5",
      // with extras to support encoding/decoding a case class hierarchy
      "io.circe" %% "circe-generic-extras" % "0.14.3"
      // (as for why the versions for circe-core and circe-generic-extras are different, see
      //  https://github.com/circe/circe-generic-extras/issues/279)
    ),

    libraryDependencySchemes ++= Seq(
      // see https://github.com/circe/circe-iteratee/issues/261
      "io.circe" %% "circe-jawn" % VersionScheme.Always,
      // see https://github.com/sbt/sbt/issues/7140#issuecomment-1464119328
      "io.circe" % "circe-jawn_2.13" % VersionScheme.Always
    ),

    Compile / scalacOptions ++= Seq(
      "-Ymacro-annotations"
    )
  )
