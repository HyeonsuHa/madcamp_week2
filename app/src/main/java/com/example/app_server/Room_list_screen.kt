package com.example.app_server

import Room_info_list
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.room_list_screen.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.ObjectInputStream

class Room_list_screen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val roomlist = Room_info_list()

        setContentView(R.layout.room_list_screen)

        val myroomlistadapter = Room_info_adapter(this, roomlist)
        room_list_recyclerview.adapter = myroomlistadapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        room_list_recyclerview.layoutManager = layout
        room_list_recyclerview.setHasFixedSize(true)

        Mysocket.mysocket.emit("enter-lobbylist")
        Mysocket.mysocket.on("enter-lobbylist") { arg ->
            val room_unique_id = JSONObject(arg[0].toString())
            Log.d("로그인4", arg[0].toString())
            val keys = room_unique_id.keys()
            val room_in_fo = arrayListOf<Room_info>()
            while (keys.hasNext()) {
                val key = keys.next();
                val value = room_unique_id.get(key)
                Log.d("로그인5",value.toString())
                val jsonobject2 = JSONObject(value.toString())
                Log.d("로그인5",jsonobject2.toString())
                val name = jsonobject2.getString("name")
                val playerNum = jsonobject2.getInt("playerNum")
                val listdata = arrayListOf<String>()
                val jsonarray2 = jsonobject2.getJSONArray("players")
                var j=0
                if(jsonarray2 != null)
                {
                    while(j < jsonarray2.length()){
                        listdata.add(jsonarray2.getString(j))
                        j++
                    }
                }
                val new_room_info : Room_info = Room_info(name,listdata,playerNum)
                room_in_fo.add(new_room_info)
                Log.d("로그인7",listdata.toString())


                Log.d("로그인6",jsonarray2.toString())

                /*var room_info : Room_info = Room_info(name.toString(), players, playerNum)*/
            }
            val newadapter = Room_info_adapter(this,room_in_fo)
            room_list_recyclerview.adapter = newadapter

        }
    }
}