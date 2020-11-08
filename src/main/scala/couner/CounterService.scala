package couner

import java.lang
import java.util.concurrent.ConcurrentHashMap

import collection.JavaConverters._
import com.google.common.hash.{BloomFilter, Funnels}

class CounterService {
  import CounterService._
  private val counters =  new ConcurrentHashMap[String, BloomWrapper](DISTICT_MESSAGE_TYPES).asScala

  def process(msg: Message): Unit = {
    counters.get(msg.event_name) match {
      case Some(counter) => counter.put(msg.value)
      case _ =>
        val counter = new BloomWrapper
        counter.put(msg.value)
        counters += msg.event_name -> counter
    }
  }

  def getCounts: collection.Map[String, Long] = {
    counters.mapValues(_.count)
  }

}

object CounterService {
  val DISTICT_MESSAGE_TYPES = 100000
  val DISTICT_VALUE_TYPES = 100000
  val PRESISION = 0.01


  class BloomWrapper{
    private val bloom: BloomFilter[lang.Long] = BloomFilter.create[lang.Long](
      Funnels.longFunnel(),
      DISTICT_VALUE_TYPES,
      PRESISION
    )

    def count: Long = synchronized({
      bloom.approximateElementCount()
    })

    def put(value: Long) = synchronized({
      bloom.put(value)
    })
  }
}

