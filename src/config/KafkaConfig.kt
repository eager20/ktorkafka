package com.eager20.rawdata.kafka

import com.eager20.config.Configuration
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*


class RawDataKafkaClient (private val config: Configuration)   {


    fun createProducer(): Producer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = config.appConf("kafka.hostname")
            .plus(":")
            .plus(config.appConf("kafka.port"))
        props["key.serializer"] = StringSerializer::class.java
        props["value.serializer"] = StringSerializer::class.java
        return KafkaProducer<String, String>(props)
    }

    fun createConsumer(): Consumer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = config.appConf("kafka.hostname")
            .plus(":")
            .plus(config.appConf("kafka.port"))
        props["group.id"] = "test03"
        props["auto.offset.reset"] = "earliest"
        props["key.deserializer"] = StringDeserializer::class.java
        props["value.deserializer"] = StringDeserializer::class.java
        return KafkaConsumer(props)
    }

}