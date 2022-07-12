package com.example.app_server

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app_server.for_socket.SocketApplication
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_9.*
import org.json.JSONObject
import org.w3c.dom.Text

class Activity9 : AppCompatActivity() {
    lateinit var MySocket: Socket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_9)

        MySocket = SocketApplication.get()
        MySocket.connect()

        val sendbutton: Button = findViewById(R.id.button_for_client)
        sendbutton.setOnClickListener {
            /*MySocket.emit("message", "hello")*/
            MySocket.send("ㅎㅇ")
            Log.d("send socket11", "내가 소켓으로 보내는 첫 메세지 제발 닿아라")
        }

        /*MySocket.on("get message", onMessage)*/
    }
    var onMessage = Emitter.Listener {args ->
    val sendText : TextView = findViewById(R.id.testtext1) as TextView
        val obj = JSONObject(args[0].toString())
        val a = sendText.text.toString()
        Log.d("main activity",obj.toString())
        Thread(object : Runnable{
            override fun run() {
                runOnUiThread(Runnable{
                    kotlin.run{
                        testtext1.text = a + "\n" + obj.get("message")
                    }
                })
            }
        }).start()
    }
}
