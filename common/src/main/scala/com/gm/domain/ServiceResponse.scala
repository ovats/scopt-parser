package com.gm.domain

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class ServiceResponse(status: String, description: String)

object ServiceResponse {
  implicit val serviceRequestCodec: Codec[ServiceResponse] = deriveCodec[ServiceResponse]
}
