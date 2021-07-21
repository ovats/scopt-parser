import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import com.gm.repository.CustomerRepository
import com.gm.routes.CustomerRoutes
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter

import scala.util.{Failure, Success}

object MainApiApp {

  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("MainApiApp")
    import system.dispatcher

    val customerRepo   = new CustomerRepository()
    val customerRoutes = new CustomerRoutes(customerRepo)
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
      .bind(routes)
      .onComplete {
        case Success(_) => println(s"Started!")
        case Failure(e) => println("Failed to start ... ", e)
      }

  }

}
