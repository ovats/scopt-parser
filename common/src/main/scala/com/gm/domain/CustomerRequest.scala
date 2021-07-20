package com.gm.domain

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class CustomerRequest(name: String)

object CustomerRequest {
  implicit val customerRequestCodec: Codec[CustomerRequest] = deriveCodec[CustomerRequest]
}
