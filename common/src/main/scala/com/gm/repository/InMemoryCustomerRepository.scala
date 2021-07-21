package com.gm.repository

import com.gm.domain.{Customer, CustomerRequest}

import java.util.UUID
import scala.collection.concurrent.TrieMap

//TODO This is just a repository in memory for the sake of the example.
class InMemoryCustomerRepository extends Repository[CustomerRequest, Customer] {

  private var customers: TrieMap[String, Customer] = TrieMap.empty

  override def create(customerRequest: CustomerRequest): Customer = {
    val newCustomer = Customer(id = UUID.randomUUID(), name = customerRequest.name)
    customers += (newCustomer.id.toString -> newCustomer)
    newCustomer
  }

  override def update(customer: Customer): Customer = {
    customers += (customer.id.toString -> customer)
    customer
  }

  override def find(id: UUID): Option[Customer] = {
    customers.get(id.toString)
  }

  override def findAll(): List[Customer] = {
    customers.toList.map(x => x._2)
  }

  override def removeAll(): Unit = {
    customers = TrieMap.empty
  }

}
