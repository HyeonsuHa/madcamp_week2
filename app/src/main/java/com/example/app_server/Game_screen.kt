package com.example.app_server

import Card
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import card_recycler_adapter2
import com.example.app_server.Mysocket.Companion.mysocket
import com.example.app_server.for_sharedpreference.MyApplication
import game_data
import kotlinx.android.synthetic.main.activity_6.*
import kotlinx.android.synthetic.main.activity_game_screen.*
import org.json.JSONArray
import org.json.JSONObject

class Game_screen : AppCompatActivity() {




    var cardList = arrayListOf<Card>(
        Card(0, "#DD2C00"),  //red
        Card(2,"#FF6F00")    //orange
    )
    var cardListmy = arrayListOf<Card>(
        Card(3,"#2E7D32"),   //green
        Card(4,"#5D4037"),    //brown
        Card(8,"#00E5FF")     //blue
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        var table_card : ArrayList<Card>
        var field : ArrayList<ArrayList<Card>>
        var hand : ArrayList<Card>
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_game_screen)

        val cardAdapter = card_recycler_adapter(this,cardList)
        game_recyclerview.adapter = cardAdapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true)
        game_recyclerview.layoutManager = layout

        mysocket.emit("시작 게임",)
        mysocket.on("시작 게임")  { arg ->
            val game_info = arg[0].toString()

        }

        val cardAdapter2 = card_recycler_adapter2(this,cardListmy)
        recylerview_for_hands.adapter = cardAdapter2

        val layout2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true)
        recylerview_for_hands.layoutManager = layout2

        /*game_card_my1.setOnClickListener({
            mysocket.emit("시작 게임",)
        })*/

        game_enemy1.setOnClickListener({
            var builder = AlertDialog.Builder(this)
            builder.setTitle("상대 정보")
            builder.setMessage("정보들")
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {

                    }
                }
            }
            builder.setPositiveButton("Ok", listener)
            builder.show()
        })
        game_enemy2.setOnClickListener({
            var builder = AlertDialog.Builder(this)
            builder.setTitle("상대 정보")
            builder.setMessage("정보들")
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {

                    }
                }
            }
            builder.setPositiveButton("Ok", listener)
            builder.show()
        })
        game_enemy3.setOnClickListener({
            var builder = AlertDialog.Builder(this)
            builder.setTitle("상대 정보")
            builder.setMessage("정보들")
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {

                    }
                }
            }
            builder.setPositiveButton("Ok", listener)
            builder.show()
        })
        game_enemy4.setOnClickListener({
            var builder = AlertDialog.Builder(this)
            builder.setTitle("상대 정보")
            builder.setMessage("정보들")
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {

                    }
                }
            }
            builder.setPositiveButton("Ok", listener)
            builder.show()
        })
        game_enemy5.setOnClickListener({
            var builder = AlertDialog.Builder(this)
            builder.setTitle("상대 정보")
            builder.setMessage("정보들")
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {

                    }
                }
            }
            builder.setPositiveButton("Ok", listener)
            builder.show()
        })
        mysocket.emit("시작 게임")
        mysocket.on("시작 게임"){arg->
            val jsonobject = JSONObject(arg[0].toString())
            val keys = jsonobject.keys()
            while(keys.hasNext())
            {
                val req = jsonobject.getBoolean("request")
                val end = jsonobject.getBoolean("End")
                val jsonarray = JSONArray("table")
                var i =0;
                val table_c = arrayListOf<Card>()
                /*while(i<jsonarray.length())
                {
                    val Card = Card(jsonarray.getJSONArray(i).getInt("number"),jsonarray.getJSONArray(i).getString("color"))
                    table_c.add()
                }*/

            }
        }
        fun manage_game () {

        }
    }
}