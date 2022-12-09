package com.eager20.rawdata.service

interface RawDataService{
    fun addWord(src:String) :String
}

class RawDataServiceImpl :RawDataService  {

    override fun addWord(src:String) :String{
        return src+" TEST"
    }

}