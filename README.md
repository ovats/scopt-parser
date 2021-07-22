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

## Endpoints defined in Api:

### GET /customers

Returns the list of customers.

Example:

```
curl localhost:8080/customer
```

### POST /customer

Add a new customer.

Example:

```
curl -X POST http://localhost:8080/customer -H "Content-Type: application/json"  -d '{"name":"John"}'
```

### GET /customer/{id}

Return the data of a customer given its id.

Example:

```
curl localhost:8080/customer/9241a211-2fd2-4184-a801-c8556c5594fd
```

### GET /docs

Show the documentation of all endpoints in Open Api Specification.

## Cli project: command line

The Cli allows to run from the command line to print the OAS in YAML format for all endpoints.
Possible arguments are:

- getcustomer
- getcustomers
- postcustomer

This is the same info as endpoint `GET /docs`.

