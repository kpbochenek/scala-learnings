package com.kpbochenek.apachekafka

import java.{util => ju}
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

// based on article:
// https://dzone.com/articles/hands-on-apache-kafka-with-scala

object KProducer {
  val topicName = "news"

  def main(args: Array[String]): Unit = {
    val props = new ju.Properties
    props.put("bootstrap.servers", KConfig.address)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("acks","all")

    val producer = new KafkaProducer[String, String](props)
    val record = new ProducerRecord[String, String](KConfig.topicName, "key", "value")

    try {
      val metadata = producer.send(record).get
      println(s"Sent record ${metadata.topic()} :: ${metadata.partition()} :: ${metadata.offset()}")
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      producer.close()
    }
  }
}
