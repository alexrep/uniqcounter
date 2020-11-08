package couner.http

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{PathMatcher, Route}
import couner.{CounterService, Message}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import JsonFormats._

object CountsRoute{

  def route(service: CounterService): Route =
    concat(
      path("reports") {
        get {
          complete(mToJSON(service.getCounts.toMap))
        }
      },
      path("data") {
        post {
          entity(as[Message]) { message =>
            service.process(message)
            complete(StatusCodes.OK)
          }
        }
      }

    )

}
