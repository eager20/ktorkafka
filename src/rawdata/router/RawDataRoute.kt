package rawdata.router

import com.eager20.rawdata.kafka.RawDataKafkaClient
import com.eager20.rawdata.repository.UserRepository
import com.eager20.rawdata.service.RawDataService
import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.apache.kafka.clients.producer.ProducerRecord
import org.koin.ktor.ext.inject
import java.time.Duration

fun Route.rawDataRoute(){
    recvGetData()
}

private fun Route.recvGetData(){

    val service by inject<RawDataService>()
    val kafkaClient by inject<RawDataKafkaClient>()
    val userRepository by inject<UserRepository>()

    get("/a") {

        val config = HoconApplicationConfig(ConfigFactory.load())

        println(config.propertyOrNull("ktor.deployment.port")?.getString())
        call.respondText(service.addWord("RAW CALL") , contentType = ContentType.Text.Plain)
    }

    //fun getProperty(key: String): String? = config.propertyOrNull(key)?.getString()

    get("/kafkacall") {
        val producer1 = kafkaClient.createProducer()
        val future = producer1.send(ProducerRecord("TP001", "1","TEST"))
        future.get()
        call.respondText("RAW CALL" , contentType = ContentType.Text.Plain)
    }

    get("/getkafka") {
        val consummer1 = kafkaClient.createConsumer()
        consummer1.subscribe(listOf("TP001"))
        var record1 = consummer1.poll(Duration.ofSeconds(1))
        println("Consumed ${record1.count()} records")
        record1.iterator().forEach {
            val message = it.value()
            println("Message: $message")
        }
        call.respondText(record1.joinToString("\n") +" DONE!" , contentType = ContentType.Text.Plain)
    }

    get("/putdata") {

        userRepository.insert()
        call.respondText( "Insert DONE!" , contentType = ContentType.Text.Plain)
    }

}