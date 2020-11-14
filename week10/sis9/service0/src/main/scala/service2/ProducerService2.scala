package service2

import java.util.Properties

import akka.actor.ActorSystem
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.{IntegerSerializer, StringSerializer}

object ProducerService2 extends App {
  def sendNewData(value: Int): Unit ={

    implicit val system = ActorSystem("QuickStart")
    implicit val ec = system.dispatcher
    val topicName = system.settings.config.getString("akka.kafka.producer.kafka-clients.topicService2")

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

    producer.send(new ProducerRecord[Int, String](topicName, 10, value.toString))

    producer.flush()
  }

}