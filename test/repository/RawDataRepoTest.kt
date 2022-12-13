package com.eager20.repository

import com.eager20.rawdata.mapper.UserConverter
import com.eager20.rawdata.model.Member
import com.eager20.rawdata.repository.UserRepository
import com.eager20.test.AbstKoinTest
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.test.inject
import org.mapstruct.factory.Mappers
import javax.sql.DataSource
import kotlin.test.*

class RawDataRepoTest : AbstKoinTest() {

    val userRepository : UserRepository by inject()
    val datasource : DataSource by inject()

    val converter = Mappers.getMapper(UserConverter::class.java) // or PersonConverterImpl()

    @Test
    fun testInsert() {
        val insertKey = userRepository.insert().value
        println("insertKey==== $insertKey")
        assertNotNull(insertKey)
    }

    @Test
    fun ExportedAllSelectTest(){

        val rtn = userRepository.selectAll()
        rtn.forEach { println(it.toString()) }
        assertFalse(rtn.isEmpty());

    }

    @Test
    fun ExportedselectAllMapStructConvertTest(){
        val rtn = userRepository.selectAllMapStructConvert()
        rtn.forEach { println(it.toString()) }
        assertFalse(rtn.isEmpty());
        //println ( userRepository.selectAllMapStructConvert().forEach { println(it.toString()) } );

    }

    @Test
    fun ExportedTest3(){
        val rtn = userRepository.selectAllSortByName()
        rtn.forEach { println( converter.ConvertToDto(it).toString()) }
        assertFalse(rtn.isEmpty());
        //println ( userRepository.selectAllSortByName().forEach { println( converter.ConvertToDto(it).toString()) } );

    }

    @Test
    fun ExportedTest4(){

        val rtn = userRepository.selectFindByName()
        rtn.forEach { println( converter.ConvertToDto(it).toString()) }
        assertFalse(rtn.isEmpty());

//        val converter = Mappers.getMapper(UserConverter::class.java) // or PersonConverterImpl()
//        println ( userRepository.selectFindByName().forEach { println( converter.ConvertToDto(it).toString()) } );

    }

}