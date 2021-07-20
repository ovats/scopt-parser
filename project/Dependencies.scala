import Dependencies.Versions._
import sbt._

object Dependencies {

  object Versions {

    val scoptVersion          = "4.0.1"
    val tapirVersion          = "0.18.0-M18"
    val circeVersion          = "0.14.1"
    val heikoseebergerVersion = "1.35.3"
  }

  object Libraries {
    val scopt = "com.github.scopt" %% "scopt" % scoptVersion

    val tapirCore  = "com.softwaremill.sttp.tapir" %% "tapir-core"             % tapirVersion
    val tapirAkka  = "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % tapirVersion
    val tapirCirce = "com.softwaremill.sttp.tapir" %% "tapir-json-circe"       % tapirVersion

    val circe          = "io.circe"          %% "circe-core"      % circeVersion
    val heikoseeberger = "de.heikoseeberger" %% "akka-http-circe" % heikoseebergerVersion

    //TODO define dependencies for each project
//    val cliDependencies = Seq(scopt)
//    val commonDependencies = Seq(tapirCore, tapirAkka, tapirCirce, circe)
//    val apiDependencies = Seq()
    val dependencies = Seq(tapirCore, tapirAkka, tapirCirce, circe)
  }

}
