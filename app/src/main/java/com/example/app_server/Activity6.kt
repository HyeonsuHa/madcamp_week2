package com.example.app_server

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_server.Mysocket.Companion.mysocket
import com.example.app_server.com.example.app_server.User_room_adapter
import kotlinx.android.synthetic.main.activity_6.*
import kotlinx.android.synthetic.main.system_message_item.view.*

class Activity6 : AppCompatActivity() {

    var textList = arrayListOf<message>(
        message("하현수"),
        message("황인준")
    )

    var userList = arrayListOf<player>(
        player("하현수",R.drawable.snowy,100,50,false),
        player("황인준",R.drawable.snowy,100,100,true),
        player("하현수",R.drawable.snowy,100,50,false),
        player("황인준",R.drawable.snowy,100,100,true),
        player("하현수",R.drawable.snowy,100,50,false),
        player("황인준",R.drawable.snowy,100,100,true),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_6)

        val messageboxAdapter = message_recycler_adapter(this,textList)
        system_message_box.adapter = messageboxAdapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true)
        system_message_box.layoutManager = layout
        system_message_box.setHasFixedSize(true)

        val userboxAdapter = User_room_adapter(this,userList)
        user_room.adapter = userboxAdapter

        val layout2 = GridLayoutManager(this, 2)
        user_room.layoutManager = layout2

        ready_button.setOnClickListener({
            val intent_to_game = Intent(this@Activity6,Game_screen::class.java)
            startActivity(intent_to_game)
        })

        mysocket.emit("enter-lobby")
    }
}