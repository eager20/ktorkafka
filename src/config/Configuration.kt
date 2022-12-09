package com.eager20.config

import com.typesafe.config.ConfigFactory
import io.ktor.config.HoconApplicationConfig
import io.ktor.util.KtorExperimentalAPI

class Configuration  {
    private val config by lazy {
        HoconApplicationConfig(ConfigFactory.load())
    }

    fun appConf(key: String) = config.property(key).getString()
}
