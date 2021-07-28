package com.gm.routes

import com.gm.domain.{Customer, CustomerRequest, ServiceResponse}
import com.gm.repository.Repository
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.{endpoint, path, Endpoint}

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

class CustomerRoutes(customerRepo: Repository[CustomerRequest, Customer])(implicit ec: ExecutionContext) {

  /**
   * Endpoints:
   *    GET   /customers        Get the list of customers
   *    GET   /customer/id      Get the list of customers
   *    POST  /customer         Add a new customer
   */

  val getAllCustomers: Endpoint[Unit, ServiceResponse, List[Customer], Any] = {
    endpoint.get
      .description("Return the list of all customers")
      .in("customers")
      .out(jsonBody[List[Customer]])
      .errorOut(jsonBody[ServiceResponse])
  }

  val getAllCustomersServerEndpoint: ServerEndpoint[Unit, ServiceResponse, List[Customer], Any, Future] = {
    getAllCustomers.serverLogic { _ =>
      Future.successful[Either[ServiceResponse, List[Customer]]](
        Right(
          customerRepo.findAll()
        )
      )
    }
  }

  val getCustomer: Endpoint[String, ServiceResponse, Customer, Any] =
    endpoint.get
      .description("Return data of a given customer")
      .in("customer")
      .in(path[String])
      .out(jsonBody[Customer])
      .errorOut(jsonBody[ServiceResponse])

  val getCustomerServerEndpoint: ServerEndpoint[String, ServiceResponse, Customer, Any, Future] = {
    getCustomer.serverLogic { id =>
      customerRepo.find(UUID.fromString(id)) match {
        case Some(c) => Future.successful[Either[ServiceResponse, Customer]](Right(c))
        case None =>
          Future.successful[Either[ServiceResponse, Customer]](
            Left(ServiceResponse(status = "error", description = s"Not found $id"))
          )
      }
    }
  }

  val createCustomer: Endpoint[CustomerRequest, ServiceResponse, Customer, Any] = {
    endpoint.post
      .description("Add a customer")
      .in("customer")
      .in(jsonBody[CustomerRequest])
      .out(jsonBody[Customer])
      .errorOut(jsonBody[ServiceResponse])
  }

  val createCustomerServerEndpoint: ServerEndpoint[CustomerRequest, ServiceResponse, Customer, Any, Future] = {
    createCustomer.serverLogic { c =>
      Future.successful[Either[ServiceResponse, Customer]](
        Right(customerRepo.create(c))
      )
    }
  }

}
