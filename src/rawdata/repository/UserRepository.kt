package com.eager20.rawdata.repository

import com.eager20.rawdata.mapper.UserConverter
import com.eager20.rawdata.model.Member
import com.eager20.rawdata.model.Member.name
import com.eager20.rawdata.model.MemberDTO
import com.eager20.rawdata.model.MemberEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.mapstruct.factory.Mappers
import javax.sql.DataSource

class UserRepository (private val datasource: DataSource) {

    fun insert() : EntityID<Int> {
        return transaction(Database.connect(datasource)){
            return@transaction Member.insert {
                it[name] = "Brandon Lee"
                it[password] = "98989999"
            }get Member.id
        }
    }

    fun selectAll() : List<MemberEntity> {
        return transaction(Database.connect(datasource)) {
            addLogger(StdOutSqlLogger)
            return@transaction MemberEntity.all().toList()
        }
    }

    fun selectAllMapStructConvert() : List<MemberDTO> {
        val converter = Mappers.getMapper(UserConverter::class.java) // or PersonConverterImpl()
        return transaction(Database.connect(datasource)) {
            addLogger(StdOutSqlLogger)
            return@transaction MemberEntity.all().map { converter.ConvertToDto(it) }.toList()
        }
    }

    fun selectAllSortByName() : List<MemberEntity> {

        return transaction(Database.connect(datasource)) {
            addLogger(StdOutSqlLogger)
            return@transaction MemberEntity.all().sortedBy { it.memSeq }
        }
    }

    fun selectFindByName() : List<MemberEntity> {

        return transaction(Database.connect(datasource)) {
            addLogger(StdOutSqlLogger)
            return@transaction MemberEntity.find { Member.name like "test%" }.toList()
        }
    }

}