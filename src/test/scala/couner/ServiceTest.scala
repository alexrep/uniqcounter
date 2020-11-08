package couner

import org.scalatest.funsuite.AnyFunSuite

import scala.util.Random

class ServiceTest extends AnyFunSuite{
 val uniq = 120
 val service =  new CounterService
 test("Should handle parallel load and return counts with required precision"){
  val messages = for {
   msgIndex <- (0 to 100)
   message = s"name-$msgIndex"
   value <- (1 to 100) ++ (90 to uniq)
  } yield Message(message, value)

  Random.shuffle(messages).par.foreach(service.process)

  assert(service.getCounts.values.forall(c => c - uniq < uniq * 0.01))
 }
}
