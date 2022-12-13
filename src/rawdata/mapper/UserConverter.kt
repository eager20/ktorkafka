package com.eager20.rawdata.mapper

import com.eager20.rawdata.model.MemberDTO
import com.eager20.rawdata.model.MemberEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface UserConverter {

    @Mapping(source = "memSeq.value" , target = "memSeq")
    fun ConvertToDto(member : MemberEntity) : MemberDTO
}