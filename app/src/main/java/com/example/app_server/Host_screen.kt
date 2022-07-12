package com.example.app_server

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.app_server.Mysocket.Companion.mysocket
import kotlinx.android.synthetic.main.host_screen.*
import org.json.JSONObject

class Host_screen : AppCompatActivity() {

    var playerNum: Int = 1
    var lobbyName: String = ""
    var name_ok: Boolean = false
    var max_num_ok: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_screen)

        unactivate_make_room_button()

        var max_button2: CardView = findViewById<CardView>(R.id.max_user_2)
        var max_text2: TextView = findViewById<TextView>(R.id.max_user_2_text)
        var max_button3: CardView = findViewById<CardView>(R.id.max_user_3)
        var max_text3: TextView = findViewById<TextView>(R.id.max_user_3_text)
        var max_button4: CardView = findViewById<CardView>(R.id.max_user_4)
        var max_text4: TextView = findViewById<TextView>(R.id.max_user_4_text)
        var max_button5: CardView = findViewById<CardView>(R.id.max_user_5)
        var max_text5: TextView = findViewById<TextView>(R.id.max_user_5_text)
        var max_button6: CardView = findViewById<CardView>(R.id.max_user_6)
        var max_text6: TextView = findViewById<TextView>(R.id.max_user_6_text)

        //edittext 코드들
        room_name_edit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (room_name_edit.editableText.length >= 1) {
                    lobbyName = room_name_edit.editableText.toString()
                    name_ok = true
                } else {
                    name_ok = false
                }
                check_make_room_button()
            }
        })

        max_button2.setOnClickListener({
            activate_max_button(max_button2, max_text2)
            unactivate_max_button(max_button3, max_text3)
            unactivate_max_button(max_button4, max_text4)
            unactivate_max_button(max_button5, max_text5)
            unactivate_max_button(max_button6, max_text6)
            playerNum = 2
            check_max_num_ok()
            check_make_room_button()
        })
        max_button3.setOnClickListener({
            unactivate_max_button(max_button2, max_text2)
            activate_max_button(max_button3, max_text3)
            unactivate_max_button(max_button4, max_text4)
            unactivate_max_button(max_button5, max_text5)
            unactivate_max_button(max_button6, max_text6)
            playerNum = 3
            check_max_num_ok()
            check_make_room_button()
        })
        max_button4.setOnClickListener({
            unactivate_max_button(max_button2, max_text2)
            unactivate_max_button(max_button3, max_text3)
            activate_max_button(max_button4, max_text4)
            unactivate_max_button(max_button5, max_text5)
            unactivate_max_button(max_button6, max_text6)
            playerNum = 4
            check_max_num_ok()
            check_make_room_button()
        })
        max_button5.setOnClickListener({
            unactivate_max_button(max_button2, max_text2)
            unactivate_max_button(max_button3, max_text3)
            unactivate_max_button(max_button4, max_text4)
            activate_max_button(max_button5, max_text5)
            unactivate_max_button(max_button6, max_text6)
            playerNum = 5
            check_max_num_ok()
            check_make_room_button()
        })
        max_button6.setOnClickListener({
            unactivate_max_button(max_button2, max_text2)
            unactivate_max_button(max_button3, max_text3)
            unactivate_max_button(max_button4, max_text4)
            unactivate_max_button(max_button5, max_text5)
            activate_max_button(max_button6, max_text6)
            playerNum = 6
            check_max_num_ok()
            check_make_room_button()
        })
        host_room_button.setOnClickListener({
            val jsonObject = JSONObject()
            jsonObject.put("lobbyName", lobbyName)
            jsonObject.put("playerNum", playerNum)

            mysocket.emit("create-lobby", jsonObject)

            mysocket.on("create-lobby") { arg ->
                val jsonobject_uid = (arg[0].toString())
                val room_uid = jsonobject_uid
                Log.d("ㅎㅇ", room_uid.toString())
                mysocket.emit("enter-lobby",room_uid)    //플레이어가 들어왔을 때,
            }
            mysocket.on("enter-lobby") { arg ->
                Log.d("arg",arg.toString())
                val jsondata = JSONObject(arg[0].toString())
                Log.d("arg",jsondata.toString())
                Log.d("arg",jsondata.getBoolean("success").toString())
                if(jsondata.getBoolean("success")){
                    Log.d("arg","성공")
                    val intent_to_wait = Intent(this@Host_screen, Waiting_screen::class.java)
                    startActivity(intent_to_wait)
                }
                else {
                    Log.d("arg","실패")
                    val message = jsondata.getString("message")
                    runOnUiThread{Toast.makeText(this@Host_screen,message, Toast.LENGTH_SHORT)}
                }
            }
        })
    }

    fun activate_max_button(button: CardView, textview: TextView) {
        button.setCardBackgroundColor(Color.parseColor("#039BE5"))
        textview.setTextColor(Color.parseColor("#FFFFFF"))
    }

    fun unactivate_max_button(button: CardView, textview: TextView) {
        button.setCardBackgroundColor(Color.parseColor("#E1F5FE"))
        textview.setTextColor(Color.parseColor("#000000"))
    }


    fun unactivate_make_room_button() {
        host_room_button.isClickable = false
        host_room_button.setCardBackgroundColor(Color.parseColor("#F5F5F5"))
        make_room_text.setTextColor(Color.parseColor("#22000000"))
    }

    fun activate_make_room_button() {
        host_room_button.isClickable = true
        host_room_button.setCardBackgroundColor(Color.parseColor("#039BE5"))
        make_room_text.setTextColor(Color.parseColor("#FFFFFF"))
    }

    fun check_max_num_ok() {
        if (playerNum >= 2 && playerNum <= 6) max_num_ok = true
        else max_num_ok = false
    }

    fun check_make_room_button() {
        if (name_ok && max_num_ok) activate_make_room_button()
        else unactivate_make_room_button()
    }
}