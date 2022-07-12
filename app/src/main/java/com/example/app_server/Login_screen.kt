package com.example.app_server

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.app_server.api.RetrofitInstance_signup
import com.example.app_server.for_sharedpreference.MyApplication
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        Log.d("로그인","생성완료")
        login_request_button.setOnClickListener({
            Log.d("로그인","눌림??")
            val myinfo : signup_data = signup_data(login_edittext_for_id.editableText.toString(),login_edittext_for_password.editableText.toString())
            val loginresponse = RetrofitInstance_signup.api_signup.login(myinfo)
            Log.d("로그인","눌림22??")
            loginresponse.enqueue(object : Callback<server_response> {
                override fun onResponse(
                    call: Call<server_response>,
                    response: Response<server_response>
                )
                {
                    if(response.isSuccessful()) {
                        Log.d("로그인","response 받기 성공")
                        Log.d("로그인",response.body()?.token.toString())
                        Log.d("로그인",response.body()?.success.toString())
                        if(response.body()!!.success){
                            val intent_to_main = Intent(this@Login_screen, Main_screen::class.java)
                            MyApplication.prefs.setString("My token",response.body()?.token.toString())
                            startActivity(intent_to_main)
                            finish()
                        }
                        else {
                            Toast.makeText(this@Login_screen,"로그인 실패 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Log.d("로그인","실패")
                    }
                }
                override fun onFailure(call: Call<server_response>, t: Throwable) {}  //실패
            })
        })
        to_signup_screen.setOnClickListener({
            val intent_to_signup = Intent(this@Login_screen, Signup_screen::class.java)
            startActivity(intent_to_signup)
        })
    }
}