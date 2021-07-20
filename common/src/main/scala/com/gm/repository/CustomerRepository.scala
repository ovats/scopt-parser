package com.gm.repository

import com.gm.domain.{Customer, CustomerRequest}

import java.util.UUID
import scala.collection.concurrent.TrieMap

//TODO This is just a repository in memory for the sake of the example.

class CustomerRepository {

  private var customers: TrieMap[String, Customer] = TrieMap.empty

  def create(customerRequest: CustomerRequest): Customer = {
    val newCustomer = Customer(id = UUID.randomUUID(), name = customerRequest.name)
    customers += (newCustomer.id.toString -> newCustomer)
    newCustomer
  }

  def update(customer: Customer): Customer = {
    customers += (customer.id.toString -> customer)
    customer
  }

  def find(id: UUID): Option[Customer] = {
    customers.get(id.toString)
  }

  def findAll(): List[Customer] = {
    customers.toList.map(x => x._2)
  }

  def removeAll: Unit = {
    customers = TrieMap.empty
  }

}
