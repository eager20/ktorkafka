package com.eager20.repository

import com.eager20.rawdata.service.RawDataService
import com.eager20.test.AbstKoinTest
import org.junit.Test
import org.koin.test.inject
import javax.sql.DataSource

class ServiceTest : AbstKoinTest() {

    val rawDataService : RawDataService by inject()

    @Test
    fun ServiceDiTest(){
        println( rawDataService.addWord("eager20"))
    }

}