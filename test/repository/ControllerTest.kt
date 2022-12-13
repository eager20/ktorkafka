package com.eager20.repository

import com.eager20.apiRoute
import com.eager20.module
import com.eager20.test.AbstKoinTest
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.koin.dsl.module

class ControllerTest {

    @Test
    fun controllerTest() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/api/v1/getdbdata").apply {
                println(response.content)
                kotlin.test.assertEquals(HttpStatusCode.OK, response.status())
                kotlin.test.assertNotNull(response.content)
            }
        }
    }

}