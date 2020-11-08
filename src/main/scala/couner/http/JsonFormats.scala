package couner.http

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import couner.Message
import spray.json.DefaultJsonProtocol.mapFormat
import spray.json._

object JsonFormats {
  import DefaultJsonProtocol._

  implicit val userJsonFormat: RootJsonFormat[Message] = jsonFormat2(Message)
  def mToJSON(m: Map[String, Long]) = mapFormat[String, Long].write(m)

}
