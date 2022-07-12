package com.example.app_server.api


import MyDataItem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import com.example.app_server.for_sharedpreference.token

interface API {

    @GET("posts/1")
    suspend fun getMyData() : MyDataItem

    @POST("auth/signup")
    suspend fun getToken() : token

    @FormUrlEncoded
    @POST("")
    suspend fun pushMyData(
        @Field("userId") userId : Int,
        @Field("id") id : Int,
        @Field("title") title : String,
        @Field("body") body : String
    ): Response<MyDataItem>
}