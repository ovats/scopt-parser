package com.gm.domain

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

import java.util.UUID

case class Customer(id: UUID, name: String)

object Customer {
  implicit val customerCodec: Codec[Customer] = deriveCodec[Customer]
}
