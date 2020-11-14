package service2

import java.time.Duration
import java.util.Properties

import akka.actor.ActorSystem
import org.apache.kafka.clients.consumer.ConsumerConfig._
import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.{IntegerDeserializer, StringDeserializer}

import scala.collection.JavaConverters._


object ConsumerService2 extends App {

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
  consumer.subscribe(List(
    system.settings.config.getString("akka.kafka.producer.kafka-clients.topicService0")
  ).asJava)

  println("| Key | Message | Partition | Offset |")
  def changeData(value:String): Int ={
    val temp = value.toInt
    return temp+2
  }
  while (true) {
    val polledRecords: ConsumerRecords[Int, String] = consumer.poll(Duration.ofSeconds(1))
    if (!polledRecords.isEmpty) {
      println(s"Polled ${polledRecords.count()} records SERVICE #2")
      val recordIterator = polledRecords.iterator()
      while (recordIterator.hasNext) {
        val record: ConsumerRecord[Int, String] = recordIterator.next()
        val csvTrip = changeData(record.value())
        ProducerService2.sendNewData(csvTrip)

        println(s"| ${record.key()} | ${csvTrip} | ${record.partition()} | ${record.offset()} |")
      }
    }
  }

}