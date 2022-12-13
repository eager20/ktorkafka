package com.eager20.di

import com.eager20.config.Configuration
import com.eager20.config.DatabaseConfig
import com.eager20.rawdata.kafka.RawDataKafkaClient
import com.eager20.rawdata.repository.UserRepository
import com.eager20.rawdata.service.RawDataService
import org.koin.dsl.module
import javax.sql.DataSource

val appModule = module {
    // application.conf 파일에서 값을 가져와 DI
    // Configuration 을 생성자로 사용하는곳(RawDataKafkaClient,DatabaseConfig) 에서 사용됨, get() 을 통해서...
    single { Configuration() }

    // get() 이라고 쓰면,
    // 생성자 파라미터가 Configuration 요기에 알아서 DI ~~!
    // 생성자 Configuration
    single { RawDataKafkaClient(get()) }

    // 생성자 Configuration 에 DI ~~!
    single<DataSource> { DatabaseConfig(get()).dataSource() }

    // 생성자 DataSource 에 DI ~~!
    single { UserRepository(get()) }

    // Service DI
    single { RawDataService() }


//    singleBy<TaskService, TaskServiceImpl>()
//    singleBy<UserService, UserServiceImpl>()
//    singleBy<TaskRepository, TaskRepositoryImpl>()
//    singleBy<UserRepository, UserRepositoryImpl>()
//
//    single { JdbcTemplate(get()) }
//    single<DataSource> { DatabaseConfig(get()).dataSource() }
//    factory { Md5Sum(MessageDigest.getInstance("MD5")) }
}