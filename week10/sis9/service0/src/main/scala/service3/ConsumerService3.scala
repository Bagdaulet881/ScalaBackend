package service3

import java.time.Duration
import java.util.Properties

import akka.actor.ActorSystem
import org.apache.kafka.clients.consumer.ConsumerConfig._
import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.{IntegerDeserializer, StringDeserializer}

import scala.collection.JavaConverters._


object ConsumerService3 extends App {

  val consumerProperties = new Properties()
  consumerProperties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  consumerProperties.setProperty(GROUP_ID_CONFIG, "group-id-2")
  consumerProperties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, classOf[IntegerDeserializer].getName)
  consumerProperties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
  consumerProperties.setProperty(AUTO_OFFSET_RESET_CONFIG, "earliest")
  consumerProperties.setProperty(ENABLE_AUTO_COMMIT_CONFIG, "false")

  val consumer = new KafkaConsumer[Int, String](consumerProperties)
  implicit val system = ActorSystem("QuickStart")
  implicit val ec = system.dispatcher

  val topics: List[String] = List(
    system.settings.config.getString("akka.kafka.producer.kafka-clients.topicService1")
    ,system.settings.config.getString("akka.kafka.producer.kafka-clients.topicService2"))
  consumer.subscribe(topics.asJava)

  println("| Key | Message | Partition | Offset |")

  def postDataToAPI(value: String, url: String): Unit ={
      import scalaj.http.{Http, HttpOptions}
      val result = Http(url).postData(s"""{"res":"${value}","json":"data"}""")
      .header("Content-Type", "application/json")
      .option(HttpOptions.readTimeout(10000)).asString
  }

  while (true) {
    val polledRecords: ConsumerRecords[Int, String] = consumer.poll(Duration.ofSeconds(1))

    if (!polledRecords.isEmpty) {
      println(s"Polled ${polledRecords.count()} records SERVICE #3")
      val recordIterator = polledRecords.iterator()
      while (recordIterator.hasNext) {
        val record: ConsumerRecord[Int, String] = recordIterator.next()
        val csvTrip = record.value()
        postDataToAPI(csvTrip,"http://localhost:8081/result")
        println(s"| ${record.key()} | ${record.value()} | ${record.partition()} | ${record.offset()} |")
      }
    }
  }

}