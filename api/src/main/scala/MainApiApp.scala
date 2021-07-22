import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.gm.repository.InMemoryCustomerRepository
import com.gm.routes.CustomerRoutes
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.openapi.circe.yaml.RichOpenAPI
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import sttp.tapir.swagger.akkahttp.SwaggerAkka

import scala.util.{Failure, Success}

object MainApiApp {

  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("MainApiApp")
    import system.dispatcher

    val customerRepo   = new InMemoryCustomerRepository()
    val customerRoutes = new CustomerRoutes(customerRepo)

    // Endpoint that will show Open API Specification
    val docRoutes = List(customerRoutes.getAllCustomers, customerRoutes.getCustomer, customerRoutes.createCustomer)
    val openAPIDocRoute = new SwaggerAkka(
      OpenAPIDocsInterpreter().toOpenAPI(docRoutes, title = "akka-http-tapir", "1.0").toYaml
    ).routes

    // API endpoints
    val routes: Route = AkkaHttpServerInterpreter().toRoute(
      List(
        customerRoutes.createCustomerServerEndpoint,
        customerRoutes.getAllCustomersServerEndpoint,
        customerRoutes.getCustomerServerEndpoint,
      )
    )

    // Start API Rest
    Http()
      .newServerAt("0.0.0.0", 8080)
      .bind(routes ~ openAPIDocRoute)
      .onComplete {
        case Success(_) => println(s"Started!")
        case Failure(e) => println("Failed to start ... ", e)
      }

  }

}
