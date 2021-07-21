package com.gm

import com.gm.Command.Command
import scopt.OParser

/*
  getcustomer
  getcustomers
  postcustomer

 */

case class Config(mode: Command = Command.Undefined)

object MainCliApp {

  def main(args: Array[String]): Unit = {

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
      case Some(Config(Command.GetCustomer))     => println("GetAllCustomers")
      case Some(Config(Command.GetAllCustomers)) => println("GetAllCustomers")
      case Some(Config(Command.PostCustomer))    => println("PostCustomer")
      case _                                     => // This should print help to the console
    }

  }
}
