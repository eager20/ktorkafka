package com.eager20.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.util.KtorExperimentalAPI

class DatabaseConfig(private val config: Configuration) {
    fun dataSource(): HikariDataSource {
        return HikariDataSource(hikariConfig())
    }

    private fun hikariConfig() = HikariConfig().apply {
        config.run {
            isAutoCommit = true
            jdbcUrl = appConf(JDBC_URL_KEY)
            poolName = appConf(POOL_NAME_KEY)
            minimumIdle = appConf(MIN_IDLE_KEY).toInt()
            driverClassName = appConf(DRIVER_CLASSNAME_KEY)
            maximumPoolSize = appConf(MAX_POOL_SIZE_KEY).toInt()
            connectionTestQuery = appConf(CONNECTION_TEST_QUERY_KEY)
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            username = appConf(USERNAME_KEY)
            password = appConf(PASSWORD_KEY)
        }
        validate()
    }

    companion object {
        private const val MIN_IDLE_KEY = "datasource.minIdle"
        private const val JDBC_URL_KEY = "datasource.jdbcUrl"
        private const val PASSWORD_KEY = "datasource.password"
        private const val USERNAME_KEY = "datasource.username"
        private const val POOL_NAME_KEY = "datasource.poolName"
        private const val MAX_POOL_SIZE_KEY = "datasource.maxPoolSize"
        private const val DRIVER_CLASSNAME_KEY = "datasource.driverClassName"
        private const val CONNECTION_TEST_QUERY_KEY = "datasource.connectionTestQuery"
    }
}
