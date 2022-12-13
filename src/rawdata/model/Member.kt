package com.eager20.rawdata.model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column


object Member : IntIdTable(columnName = "mem_seq") {
    //val memSeq: Column<Int> = integer("mem_seq").autoIncrement().uniqueIndex()
    val name: Column<String> = varchar("name", 50)
    val password: Column<String> = varchar("password", 50)
}
