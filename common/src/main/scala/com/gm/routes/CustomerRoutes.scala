package com.gm.routes

import com.gm.domain.{Customer, CustomerRequest, ServiceResponse}
import com.gm.repository.CustomerRepository
import sttp.tapir.{endpoint, Endpoint}
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._
import sttp.tapir.server.ServerEndpoint

import scala.concurrent.{ExecutionContext, Future}

class CustomerRoutes(customerRepo: CustomerRepository)(implicit ec: ExecutionContext) {

  /**
   * Endpoints:
   *    GET   /customer/all     Get the list of customers
   *    POST  /customer         Add a new customer
   */

  val getAllCustomers: Endpoint[Unit, ServiceResponse, List[Customer], Any] =
    endpoint.get
      .in("customers")
      .out(jsonBody[List[Customer]])
      .errorOut(jsonBody[ServiceResponse])

  val getAllCustomersServerEndpoint: ServerEndpoint[Unit, ServiceResponse, List[Customer], Any, Future] = {
    getAllCustomers.serverLogic { _ =>
      Future.successful[Either[ServiceResponse, List[Customer]]](
        Right(
          customerRepo.findAll()
        )
      )
    }
  }

  val createCustomer: Endpoint[CustomerRequest, ServiceResponse, Customer, Any] =
    endpoint.post
      .in("customer")
      .in(jsonBody[CustomerRequest])
      .out(jsonBody[Customer])
      .errorOut(jsonBody[ServiceResponse])

  val createCustomerServerEndpoint: ServerEndpoint[CustomerRequest, ServiceResponse, Customer, Any, Future] =
    createCustomer.serverLogic { c =>
      Future.successful[Either[ServiceResponse, Customer]](
        Right(customerRepo.create(c))
      )
    }

}
