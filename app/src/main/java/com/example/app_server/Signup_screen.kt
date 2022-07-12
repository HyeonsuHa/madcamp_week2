package com.example.app_server

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.app_server.api.RetrofitInstance_signup
import com.example.app_server.for_sharedpreference.MyApplication
import kotlinx.android.synthetic.main.signup_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Signup_screen : AppCompatActivity() {

    //회원가입 버튼 활성화 하게 하는 변수들
    private var id_cond_ok : Boolean = false
    private var password_cond_ok : Boolean =false
    private var passconfirm_cond_ok : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_screen)

        unactivatesignupbutton()  // 바로 회원가입 못하게 버튼 비활성화하기


        // textchangelistener모음집
        //아이디 입력창 전용 textchangedlistener
        signuppage_edittext_for_id.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {   //요기서 회원가입 관련 메세지 상태 변경
                if(get_length(signuppage_edittext_for_id.editableText)>=1 && get_length(signuppage_edittext_for_id.editableText)<=25)
                {
                    message_box_id.text = "형식에 맞는 아이디입니다."
                    message_box_id.setTextColor(Color.parseColor("#00E676"))
                    id_cond_ok = true
                }
                else
                {
                    message_box_id.text = "올바르지 않은 아이디 형식입니다. (1자 이상, 25자 이하)"
                    message_box_id.setTextColor(Color.parseColor("#DD2C00"))
                    id_cond_ok = false
                }
                check_signup_cond(id_cond_ok,password_cond_ok,passconfirm_cond_ok)  //text바꾸면 회원가입 버튼 활성화 여부 검사 및 결정
            }
        })
        //비밀번호 입력창 전용 textchangedlistener
        signuppage_edittext_for_password.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {   //요기서 회원가입 관련 메세지 상태 변경
                if(get_length(signuppage_edittext_for_password.editableText)>=1 && get_length(signuppage_edittext_for_password.editableText)<=16)
                {
                    message_box_password.text = "형식에 맞는 비밀번호입니다."
                    message_box_password.setTextColor(Color.parseColor("#00E676"))
                    password_cond_ok = true
                }
                else
                {
                    message_box_password.text = "올바르지 않은 비밀번호 형식입니다. (1자 이상, 16자 이하)"
                    message_box_password.setTextColor(Color.parseColor("#DD2C00"))
                    password_cond_ok = false
                }
                check_signup_cond(id_cond_ok,password_cond_ok,passconfirm_cond_ok)  //text바꾸면 회원가입 버튼 활성화 여부 검사 및 결정
            }
        })
        //비밀번호 확인창 전용 textchangedlistener
        signuppage_edittext_for_passconfirm.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {   //요기서 회원가입 관련 메세지 상태 변경
                if(signuppage_edittext_for_passconfirm.editableText.toString().equals(signuppage_edittext_for_password.editableText.toString()))
                {
                    message_box_passconfirm.text = "비밀번호 확인 완료"
                    message_box_passconfirm.setTextColor(Color.parseColor("#00E676"))
                    passconfirm_cond_ok = true
                }
                else
                {
                    message_box_passconfirm.text = "비밀번호와 값이 다릅니다."
                    message_box_passconfirm.setTextColor(Color.parseColor("#DD2C00"))
                    passconfirm_cond_ok = false
                }
                check_signup_cond(id_cond_ok,password_cond_ok,passconfirm_cond_ok)  //text바꾸면 회원가입 버튼 활성화 여부 검사 및 결정
            }
        })

        signup_request_button.setOnClickListener({
            val myinfo : signup_data = signup_data(signuppage_edittext_for_id.editableText.toString(),signuppage_edittext_for_password.editableText.toString())
            val callgetresponse = RetrofitInstance_signup.api_signup.pushsignup_data(myinfo)

            callgetresponse.enqueue(object : Callback<server_response> {
                override fun onResponse(
                    call: Call<server_response>,
                    response: Response<server_response>
                )
                {
                    if(response.isSuccessful()) {
                        Log.d("메세지","성공")
                        Log.d("메세지",response.body()?.token.toString())
                        if(response.body()!!.success){
                            MyApplication.prefs.setString("My token",response.body()?.token.toString())
                            Log.d("메세지","저장완료")
                        }

                        else {

                        }
                    }
                    else{
                        Log.d("메세지","실패")
                    }
                }

                override fun onFailure(call: Call<server_response>, t: Throwable) {}  //실패
            })
            Toast.makeText(this, "계정 생성 완료", Toast.LENGTH_SHORT).show()
            finish()

        })
    }

    
    private fun unactivatesignupbutton() {
        signup_request_button.isClickable = false
        signup_request_button_text.text = "X"
    }

    private fun activatesignupbutton() {
        signup_request_button.isClickable = true
        signup_request_button_text.text = "회원가입"
    }

    fun get_length(inputstring :Editable) : Int {
        return inputstring.length
    }

    fun check_signup_cond(id_c : Boolean, password_c : Boolean, passconfirm_c : Boolean) {
        if(id_c && password_c && passconfirm_c){
            activatesignupbutton()
        }
        else
        {
            unactivatesignupbutton()
        }
    }


}