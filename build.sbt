import Dependencies.Libraries._

name := "scopt-parser"

version := "0.1"

val scalaLangVersion = "2.13.6"
val projectName      = "scopt-parser"

// Settings for all projects
lazy val settings = Seq(
  scalaVersion := scalaLangVersion,
  scalacOptions ++= Seq(
    "-unchecked",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-deprecation",
    "-Xfatal-warnings",
    "-encoding",
    "utf8",
  ),
  exportJars := true,
)

lazy val rootProject = project
  .in(file("."))
  .settings(
    name := "scopt-parser",
    description := projectName,
    scalaVersion := scalaLangVersion,
  )
  .aggregate(common, api, cli)

lazy val common = project
  .settings(
    name := "common",
    settings,
    libraryDependencies ++= dependencies,
  )

lazy val api = project
  .settings(
    name := "api",
    settings,
    libraryDependencies ++= dependencies,
  )
  .dependsOn(common)

lazy val cli = project
  .settings(
    name := "cli",
    settings,
    libraryDependencies ++= dependencies,
  )
  .dependsOn(common)
