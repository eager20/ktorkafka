package com.eager20.repository

import com.eager20.module
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test

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