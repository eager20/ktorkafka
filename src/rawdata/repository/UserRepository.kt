package com.eager20.rawdata.repository

import com.eager20.rawdata.model.Member
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

class UserRepository (private val datasource: DataSource) {

    fun insert(){
        transaction(Database.connect(datasource)){
            val id = Member.insert {
                it[name] = "Brandon Lee"
            }
            println(id)
        }
    }

}