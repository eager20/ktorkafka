package com.eager20.rawdata.model


import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MemberEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MemberEntity>(Member)

    var memSeq by Member.id
    var name by Member.name
    var password by Member.password
}

//val memSeq: Column<Int> = Member.integer("mem_seq").autoIncrement().uniqueIndex()
//val name: Column<String> = Member.varchar("name", 50)
//val password: Column<String> = Member.varchar("password", 50)