package com.gm

import akka.actor.ActorSystem
import com.gm.repository.InMemoryCustomerRepository
import com.gm.routes.CustomerRoutes
import scopt.OParser
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.openapi.circe.yaml.RichOpenAPI

/*
  getcustomer
  getcustomers
  postcustomer
 */

object MainCliApp {

  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("MainCliApp")
    import system.dispatcher

    val customerRepo   = new InMemoryCustomerRepository()
    val customerRoutes = new CustomerRoutes(customerRepo)

    // Arguments/Commands available
    val builder = OParser.builder[Config]
    val parser1 = {
      import builder._

      val cmdGetCustomer = OParser.sequence(
        cmd(Command.GetCustomer.toString.toLowerCase)
          .action((v, c) => c.copy(mode = Command.GetCustomer))
          .text("Get OAS in YAML format of endpoint GET /customer/{id}\n")
      )
      val cmdGetAllCustomers = OParser.sequence(
        cmd(Command.GetAllCustomers.toString.toLowerCase)
          .action((_, c) => c.copy(mode = Command.GetAllCustomers))
          .text("Get OAS in YAML format of endpoint GET /customers\n")
      )
      val cmdPostCustomer = OParser.sequence(
        cmd(Command.PostCustomer.toString.toLowerCase)
          .action((_, c) => c.copy(mode = Command.PostCustomer))
          .text("Get OAS in YAML format of endpoint POST /customer\n")
      )

      OParser.sequence(
        programName("MainCliApp"),
        head("MainCliApp"),
        help('h', "help").text("prints this usage text"),
        cmdGetCustomer,
        cmdGetAllCustomers,
        cmdPostCustomer,
      )
    }

    // Run the command
    OParser.parse(parser1, args, Config()) match {
      case Some(Config(Command.GetCustomer)) =>
        println("GetCustomer endpoint:")
        println(OpenAPIDocsInterpreter().toOpenAPI(customerRoutes.getCustomer, title = "MainCliApp", "1.0").toYaml)
      case Some(Config(Command.GetAllCustomers)) =>
        println("GetAllCustomers endpoint:")
        println(OpenAPIDocsInterpreter().toOpenAPI(customerRoutes.getAllCustomers, title = "MainCliApp", "1.0").toYaml)
      case Some(Config(Command.PostCustomer)) =>
        println("PostCustomer endpoint:")
        println(OpenAPIDocsInterpreter().toOpenAPI(customerRoutes.createCustomer, title = "MainCliApp", "1.0").toYaml)
      case _ => // This should print help to the console
    }

  }
}
