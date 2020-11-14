package service0

import java.util.Properties

import akka.actor.ActorSystem
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.{IntegerSerializer, StringSerializer}

object Publisher extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val ec = system.dispatcher

  val topicName = system.settings.config.getString("akka.kafka.producer.kafka-clients.topicService0")

  val producerProperties = new Properties()

  producerProperties.setProperty(
    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"
  )
  producerProperties.setProperty(
    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[IntegerSerializer].getName
  )
  producerProperties.setProperty(
    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName
  )

  val producer = new KafkaProducer[Int, String](producerProperties)
//publishing numbers SERVICE#0
  producer.send(new ProducerRecord[Int, String](topicName, 10, "10"))
  producer.send(new ProducerRecord[Int, String](topicName, 20, "20"))
  producer.send(new ProducerRecord[Int, String](topicName, 30, "30"))
  producer.send(new ProducerRecord[Int, String](topicName, 40, "40"))
  producer.send(new ProducerRecord[Int, String](topicName, 50, "50"))
  producer.send(new ProducerRecord[Int, String](topicName, 60, "60"))

  producer.flush()
}