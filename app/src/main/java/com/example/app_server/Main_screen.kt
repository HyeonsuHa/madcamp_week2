package com.example.app_server

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_server.Mysocket.Companion.mysocket
import com.example.app_server.for_sharedpreference.MyApplication
import com.example.app_server.for_socket.SocketApplication
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_9.*
import kotlinx.android.synthetic.main.main_screen.*
import kotlinx.android.synthetic.main.room_list_screen.*
import org.json.JSONObject

class Main_screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)
        //socket 시작 즉,(서버 접속)
        mysocket
        mysocket.connect()

        mysocket.send("사용자 접속했어용")
/*        mainsocket.emit("enter-lobbylist","너에게 보내는 첫 마디")
        mainsocket.emit("leave-lobbylist","떠나가며 또 한 마디")*/

        first_room_enter_button.setOnClickListener({
            val intent_to_host = Intent(this@Main_screen, Host_screen::class.java)
            startActivity(intent_to_host)
        })
        second_room_enter_button.setOnClickListener({
            /*val act2_intent2 = Intent(this,Activity5::class.java)
            startActivity(act2_intent2)*/
        })
        third_room_enter_button.setOnClickListener({
            val intent_to_public = Intent(this@Main_screen, Room_list_screen::class.java)
            startActivity(intent_to_public)
        })
    }
}