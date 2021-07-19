# scopt-parser


## About Scopt 

Scopt is a library available [here](https://github.com/scopt/scopt).
What is this library for? As they said:

`scopt is a little command line options parsing library.`

Nowadays, the latest version is 4.0.1. To add it to your project, modify your `build.sbt`:

```scala
val scoptVersion = "4.0.1"
libraryDependencies += "com.github.scopt" %% "scopt" % scoptVersion
```

## About this project

This is a sbt multi-project:

- api: a simple rest API.
- cli: command line using Scopt.
- common: project for common classes, traits, objects.