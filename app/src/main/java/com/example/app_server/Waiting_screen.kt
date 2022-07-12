package com.example.app_server
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewDebug
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_server.Mysocket.Companion.mysocket
import kotlinx.android.synthetic.main.waiting.*
import org.json.JSONObject
import kotlin.math.absoluteValue

class Waiting_screen : AppCompatActivity() {

    var current : Int = 1
    var max : Int = 6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.waiting)

        set_layouts(current,max)

        mysocket.on("new-player-enter-room") {arg ->

            Log.d("기달",arg.toString())
            val jsonObject = JSONObject(arg[0].toString())
            get_set_data(jsonObject)
            set_layouts(current,max)
        }
        mysocket.on("player-left-room") {arg ->
            val jsonObject = JSONObject(arg[0].toString())
            get_set_data(jsonObject)
            set_layouts(current,max)
        }
        mysocket.on("start-game") {arg ->
            val jsonObject = JSONObject(arg[0].toString())
            if(jsonObject.getBoolean("success"))
            {
                val intent_to_game = Intent(this@Waiting_screen, Game_screen::class.java)
                startActivity(intent_to_game)
                finish()
            }
            else
            {
                runOnUiThread{Toast.makeText(this@Waiting_screen,"아직 게임을 시작할 수 없습니다.",Toast.LENGTH_SHORT)}
            }
        }

        /*game_start.setOnClickListener({
            mysocket.emit("click-game-start-btn")

            mysocket.on("click-game-start-btn") { arg ->
                Log.d("하위",arg.toString())
                val jsondata2 = JSONObject(arg[0].toString())
                val IsSuccess = jsondata2.getBoolean("success")
                *//*if(IsSuccess) {
                    val intent_to_game = Intent(this@Waiting_screen, Game_screen::class.java)
                    mysocket.emit("leave-lobby")
                    startActivity(intent_to_game)
                }
                else{
                    Toast.makeText(this@Waiting_screen,"아직 정원이 차지 않았습니다.",Toast.LENGTH_SHORT)
                }*//*
            }
        })*/

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("뒤","ㄱㅊ")
        mysocket.emit("player-left-room")
        finish()
    }
    fun set_layouts(current : Int, max : Int) {
        waiting_message_box.setText("플레이어를 기다리는 중 ... "+getstring(current)+"/"+getstring(max))
    }
    fun getstring(num : Int) : String
    {
        if(num == 1) return "1"
        else if(num == 2) return "2"
        else if(num == 3) return "3"
        else if(num == 4) return "4"
        else if(num == 5) return "5"
        else if(num == 6) return "6"
        else return "00"
    }
    fun get_set_data( inputobject : JSONObject)  {
        val keys = inputobject.keys()
        while (keys.hasNext()) {
            val key = keys.next();
            val value = inputobject.get(key)
            val jsonobject2 = JSONObject(value.toString())

            val playerNum = jsonobject2.getInt("playerNum")
            val listdata = arrayListOf<String>()
            val jsonarray2 = jsonobject2.getJSONArray("players")
            var j=0
            if(jsonarray2 != null)
            {
                set_layouts(jsonarray2.length(),playerNum)
                current = jsonarray2.length()
                max = playerNum
            }
        /*var room_info : Room_info = Room_info(name.toString(), players, playerNum)*/
        }
    }

}