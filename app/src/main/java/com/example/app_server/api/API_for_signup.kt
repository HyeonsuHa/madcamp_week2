package com.example.app_server.api

import com.example.app_server.for_sharedpreference.token
import com.example.app_server.server_response
import com.example.app_server.signup_data
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface API_for_signup {


    @POST("auth/signup")  //여기에 주소 적기 좀따
    @Headers("content-type:application/json")
    fun pushsignup_data(
        @Body params : signup_data
    ):  Call<server_response>


    @POST("auth/login")
    @Headers("content-type:application/json")
    fun login(
        @Body params : signup_data
    ): Call<server_response>
    /*@POST("auth/signup")
    @Headers("content-type: application/json")
    fun post_users(
        @Body jsonparams : signup_data
    )*/

    //요 아래로 테스트중

}