package com.eager20.di

import com.eager20.config.Configuration
import com.eager20.config.DatabaseConfig
import com.eager20.rawdata.kafka.RawDataKafkaClient
import com.eager20.rawdata.repository.UserRepository
import com.eager20.rawdata.service.RawDataService
import com.eager20.rawdata.service.RawDataServiceImpl
import org.koin.dsl.module
import org.koin.dsl.single
import javax.sql.DataSource

val appModule = module {
    // application.conf 파일에서 값을 가져와 DI
    single {
        Configuration()
    }

    single<RawDataService> { RawDataServiceImpl() }

    // get() 이라고 쓰면 RawDataKafkaClient 생성자 파라미터가
    // Configuration 요거 이기에 DI 컨테이너에 이름이 같다면 알아서 주입~!
    single { RawDataKafkaClient(get()) }
    //single { DatabaseConfig(get()) }
    single<DataSource> { DatabaseConfig(get()).dataSource() }

    single { UserRepository(get()) }

//    singleBy<TaskService, TaskServiceImpl>()
//    singleBy<UserService, UserServiceImpl>()
//    singleBy<TaskRepository, TaskRepositoryImpl>()
//    singleBy<UserRepository, UserRepositoryImpl>()
//
//    single { JdbcTemplate(get()) }
//    single<DataSource> { DatabaseConfig(get()).dataSource() }
//    factory { Md5Sum(MessageDigest.getInstance("MD5")) }
}