package com.example.app_server.for_api_connect.repository.viewModelFactory

import MyDataItem
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_server.for_api_connect.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response
import com.example.app_server.for_sharedpreference.token
import com.example.app_server.server_response
import com.example.app_server.signup_data
import retrofit2.Call

class MainViewModel(private val repository: Repository) : ViewModel() {


    val myResponse: MutableLiveData<MyDataItem> = MutableLiveData()
    val myResponse2: MutableLiveData<token> = MutableLiveData()

    val myPushResponse : MutableLiveData<Response<MyDataItem>> = MutableLiveData()

    /*fun getMyData() {
        viewModelScope.launch {
            val response =repository.getMyData()
            myResponse.value = response
        }
    }*/

    fun getToken() {
        viewModelScope.launch {
            val response2 = repository.getToken()
            myResponse2.value = response2
        }
    }

    fun pushMyData(userId:Int, id : Int, title : String, body :String) {
        viewModelScope.launch {
            val response2 =repository.pushMyData(userId, id, title, body)
            myPushResponse.value = response2
        }
    }
    //요 아래는 signup용
    val myPushResponse_signup : MutableLiveData<Call<server_response>> = MutableLiveData()

    fun pushsignup_data(myinfo : signup_data) {
        viewModelScope.launch {
            val response_singup = repository.pushsignup_data(myinfo)
            myPushResponse_signup.value = response_singup
            Log.d("분석중", myPushResponse_signup.value.toString())
        }
    }
}