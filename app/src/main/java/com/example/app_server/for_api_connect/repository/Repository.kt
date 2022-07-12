package com.example.app_server.for_api_connect.repository

import MyDataItem
import com.example.app_server.api.RetrofitInstance
import com.example.app_server.api.RetrofitInstance_signup
import retrofit2.Response
import com.example.app_server.for_sharedpreference.token
import com.example.app_server.server_response
import com.example.app_server.signup_data
import retrofit2.Call

class Repository {
    suspend fun getMyData(): MyDataItem{
        return RetrofitInstance.api.getMyData()
    }

    suspend fun getToken() : token {
        return RetrofitInstance.api.getToken()
    }

    suspend fun pushMyData(userId : Int, id : Int, title : String, body : String) : Response<MyDataItem>
    {
        return RetrofitInstance.api.pushMyData(userId, id, title, body)
    }

    //아래부터는 연습용아니고 실제 signup용 method들.   //요놈은 signup관련 정보들을 push하기 위한 놈
    suspend fun pushsignup_data(myinfo : signup_data) : Call<server_response>
    {
        return RetrofitInstance_signup.api_signup.pushsignup_data(myinfo)
    }
}