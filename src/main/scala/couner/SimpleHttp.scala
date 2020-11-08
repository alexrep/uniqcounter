package couner
import scala.concurrent.ExecutionContext.Implicits.global

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import couner.http.CountsRoute

object SimpleHttp extends App {

  implicit val system: ActorSystem = ActorSystem("simple-http")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  implicit val log = Logging(system, "main")

  val port = 8080
  val service = new CounterService

  val bindingFuture =
    Http().bindAndHandle(CountsRoute.route(service), "0.0.0.0", port)

  log.info(s"Server started at the port $port")
  bindingFuture.onComplete(_ =>   log.info("Started"))
}
