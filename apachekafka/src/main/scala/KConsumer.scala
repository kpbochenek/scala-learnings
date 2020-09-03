package com.kpbochenek.apachekafka

import java.{util => ju}
import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._


object KConsumer {

  def main(args: Array[String]): Unit = {
    val props = new ju.Properties
    props.put("group.id", "grp3")
    props.put("bootstrap.servers", KConfig.address)
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")

    val consumer = new KafkaConsumer(props)
    
    try {
      consumer.subscribe(List(KConfig.topicName).asJava)
      var iter = 0
      while(iter < 10) {
        iter += 1
        val records = consumer.poll(100)
        for (record <- records.asScala) {
          println("Topic: " + record.topic() + 
               ",Key: " + record.key() +  
               ",Value: " + record.value() +
               ", Offset: " + record.offset() + 
               ", Partition: " + record.partition())
        }
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      consumer.close()
    }
  }
}
