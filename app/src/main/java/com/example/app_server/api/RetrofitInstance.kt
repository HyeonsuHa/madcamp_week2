package com.example.app_server.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.249.18.122:80/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : API by lazy {
        retrofit.create(API::class.java)
    }
}