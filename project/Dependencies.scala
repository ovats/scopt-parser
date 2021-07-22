import Dependencies.Versions._
import sbt._

object Dependencies {

  object Versions {
    val scoptVersion = "4.0.1"
    val tapirVersion = "0.18.0-M18"
    val circeVersion = "0.14.1"
  }

  object Libraries {
    val scopt = "com.github.scopt" %% "scopt" % scoptVersion

    val tapirCore  = "com.softwaremill.sttp.tapir" %% "tapir-core"               % tapirVersion
    val tapirAkka  = "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server"   % tapirVersion
    val tapirCirce = "com.softwaremill.sttp.tapir" %% "tapir-json-circe"         % tapirVersion
    val tapirDocs  = "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs"       % tapirVersion
    val tapirYaml  = "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % tapirVersion
    //TODO check exclude
    val tapirUI =
      "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-akka-http" % tapirVersion exclude ("com.typesafe.akka", "akka-stream_2.12")

    val circe = "io.circe" %% "circe-core" % circeVersion

    //TODO define dependencies for each project
//    val cliDependencies = Seq(scopt)
//    val commonDependencies = Seq(tapirCore, tapirAkka, tapirCirce, circe)
//    val apiDependencies = Seq()
    val dependencies = Seq(tapirCore, tapirAkka, tapirCirce, tapirDocs, tapirYaml, tapirUI, circe, scopt)
  }

}
