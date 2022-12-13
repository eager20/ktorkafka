package com.eager20.rawdata.service

// 인터페이스를 만들어도 되긴하는데..
// 클래스만 만드는 방식도됨....
//interface RawDataService{
//    fun addWord(src:String) :String
//}

//class RawDataServiceImpl :RawDataService  {
class RawDataService  {

    fun addWord(src:String) :String{
        return src+" TEST"
    }

}