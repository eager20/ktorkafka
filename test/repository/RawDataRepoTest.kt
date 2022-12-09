package com.eager20.repository

import com.eager20.rawdata.repository.UserRepository
import com.eager20.test.AbstKoinTest
import org.koin.test.inject
import kotlin.test.*

class RawDataRepoTest : AbstKoinTest() {

    val userRepository : UserRepository by inject()

    @Test
    fun testInsert() {
        userRepository.insert()
    }

}