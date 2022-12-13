package rawdata.router

import com.eager20.rawdata.kafka.RawDataKafkaClient
import com.eager20.rawdata.mapper.UserConverter
import com.eager20.rawdata.model.Member
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
import org.mapstruct.factory.Mappers
import java.time.Duration

fun Route.rawDataRoute(){
    recvGetData()
}

private fun Route.recvGetData(){

    val service by inject<RawDataService>()
    val kafkaClient by inject<RawDataKafkaClient>()
    val userRepository by inject<UserRepository>()

    get("/a") {
        // application.conf 파일 가져오는 셈플인데.. Configuration.kt에도 구현되어 있음.
        val config = HoconApplicationConfig(ConfigFactory.load())
        println(config.propertyOrNull("ktor.deployment.port")?.getString())

        call.respondText(service.addWord("RAW CALL") , contentType = ContentType.Text.Plain)
    }

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

        // auto_offset_reset 옵션을 earliest (첨부터 읽기를 했기 때문에 가능)
        // Web 환경에서는 kafka 컴슈머 테스트하기가.... 들어오자 마자 읽기때문에..
        // 첨부터 읽이를 통해서 데이터 들어갔는지 테스트 정도만 가능할듯~!
        record1.iterator().forEach {
            val message = it.value()
            println("Message: $message")
        }
        call.respondText(record1.joinToString("\n") +" DONE!" , contentType = ContentType.Text.Plain)
    }

    // MemberEntity와 같이 exposed Entity 리턴 시 에러 발생함..
    // Data 클래스에 값을 넣어줘 리턴해줘야함. (난 MapStruct 매퍼 이용했음.)
    get("/getdbdata") {
        call.respond(userRepository.selectAllMapStructConvert())
    }

}