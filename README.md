#Ktor 기반 Kafka Client, Exposed, MapStruct with Configuration 설정. 
- https://github.com/eager20/ktorkafka
> 처음엔 Ktor를 기반으로 Koin 이용해서 카프카 클라이언트 DI 해서만 쓸라고 했는데.... 일이 커졌다.
> 
> 토이프로젝트?를 하면서 본것들.. 작업했을때 간단한 컨셉등을 정리해보려고한다.

## Injection Tech
- Kotlin 1.6.21
  - 필요성?1 : https://gun0912.tistory.com/81
  - ScopeFunc : https://github.com/CodingBakery/KotlinStudy/issues/7
  - 1급객체 : https://medium.com/@lazysoul/functional-programming-%EC%97%90%EC%84%9C-1%EA%B8%89-%EA%B0%9D%EC%B2%B4%EB%9E%80-ba1aeb048059
  - 기초 추천 책 : http://www.yes24.com/Product/Goods/74035266
  - 고급 추천 책?(간단히만본.. 아직 못본..) : http://www.yes24.com/Product/Goods/115221699
- Ktor 1.5.1 (Kotlin MVC 모델)
  - Ktor 공식페이지 : https://ktor.io/docs/welcome.html
  - Ktor 셈플1 : https://github.com/technical-learn-room/ktor-learn
- Koin 3.2.2 (Kotlin DI 솔루션) 
  - Koin-ktor 3.1.4
  - Koin 공식페이지 : https://insert-koin.io/docs/reference/koin-ktor/ktor
  - Koin Version : https://github.com/InsertKoinIO/koin/blob/main/CHANGELOG.md
  - Koin 셈플 Git : <https://github.com/crisunx/ktor-koin-sample-app>
  - Koin 셈플1 : https://kotlinworld.com/120
- Kakfa Client
  - 셈플페이지1 : https://docs.confluent.io/platform/current/tutorials/examples/clients/docs/kotlin.html#produce-records
  - 셈플페이지2 :https://medium.com/swlh/async-messaging-with-kotlin-and-kafka-488e399e4e17
- exposed 0.36.2 (Kotlin ORM)
  - exposed 공식페이지 : https://github.com/JetBrains/Exposed/wiki
- MapStruct For Kotlin 1.5.1
  - MapStruct 공식 가이드 : https://github.com/mapstruct/mapstruct-examples/tree/main/mapstruct-kotlin
  
## 구성하면서 솔루션내 팁?
- Ktor
  - https://github.com/technical-learn-room/ktor-learn 에 셈플을 이용해서 라우터의 구성이런것을을 많이 차용했다.
  - 초기 설정은 IntelliJ 이니설라이징 설정으로 만들었다.
  - [어플리케이션설정파일](./resources/application.conf) 에 데이터르 가저와 DI할때 써야 하는데... [요렇게](src/config/Configuration.kt) 설정해서 [요렇게](src/di/AppModule.kt) 사용했다.
- Koin
  - **get()을 사용하면 컨테이너에 등록된 클래스명으로 자연스럽게 주입된다.** 
    - ex) Configuration 을 생성자로 받은곳에 get() 하면 Configuration 가 DI 된다.
- exposed
  - DSL, DAO 방식 2가지가 있는데... (https://github.com/JetBrains/Exposed/wiki)
  - 데이터를 가져와 핸들링하면 DAO 방식을 사용해야 한다.
  - DSL은 코드상으로 처리(CUD) 처리는 가능하나 데이터화 해서 조회 하는데에는 한게가 있다.
  - DAO 방식으로 하면 object 외 Entity를 만들어야 하는데...
  - Entity로 값을 가져와 println 찍는건 문제가 없는데 (toList사용해야함~!)... Response 하려면 또 DTO에 담아줘야 한다. (당연한거지만...) [요기참조](src/rawdata/repository/UserRepository.kt)
- MapStruct
  - EntityToDTO는 MapSturct를 이용했는데.. DSL Object를 이용한 MapStruct 매핑은 안되었지만.. DAO Entity를 이용한 MapStruct는 잘되서.. ㅋㅋㅋ 다행이였다.

- TEST
  - Koin으로 테스트 할르믄 [요걸](test/test/AbstKoinTest.kt) 만들어 상속받아 [사용해야](test/repository/RawDataRepoTest.kt) 했다.
  - EndPoint 테스트도 [요렇게](test/repository/ControllerTest.kt) 할 수 있어서 좋았다~!

### TABLE
``````
CREATE TABLE `member` (
`mem_seq` bigint(20) NOT NULL AUTO_INCREMENT,
`alias` varchar(100) DEFAULT NULL,
`name` varchar(20) DEFAULT NULL,
`password` varchar(200) DEFAULT NULL,
`tel` varchar(200) DEFAULT NULL,
`email` varchar(100) DEFAULT NULL,
`sex` varchar(100) DEFAULT NULL,
PRIMARY KEY (`mem_seq`),
UNIQUE KEY `member_email_IDX` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;
```