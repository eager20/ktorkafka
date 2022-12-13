package com.eager20.repository

import com.eager20.rawdata.service.RawDataService
import com.eager20.test.AbstKoinTest
import org.junit.Test
import org.koin.test.inject

class ServiceTest : AbstKoinTest() {

    private val rawDataService : RawDataService by inject()

    @Test
    fun serviceDiTest(){
        println( rawDataService.addWord("eager20"))
    }

}