package com.gm

object Command extends Enumeration {
  type Command = Value
  val GetCustomer, GetAllCustomers, PostCustomer, Undefined = Value
}
