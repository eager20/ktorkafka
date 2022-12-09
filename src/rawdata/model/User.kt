package com.eager20.rawdata.model

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


object Member : Table() {
    val memSeq: Column<Int> = integer("mem_seq").autoIncrement()
    val name: Column<String> = varchar("name", 50)
    val password: Column<String> = varchar("director", 50)
    override val primaryKey = PrimaryKey(memSeq, name = "PK_memSeq") // PK_StarWarsFilms_Id is optional here
}
