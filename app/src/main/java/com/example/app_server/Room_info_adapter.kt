package com.example.app_server

import Room_info_list
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_server.Mysocket.Companion.mysocket
import com.example.app_server.for_sharedpreference.MyApplication
import kotlinx.coroutines.NonCancellable.start
import org.json.JSONObject

class Room_info_adapter(private val context: Context, private val dataList: ArrayList<Room_info>) :
    RecyclerView.Adapter<Room_info_adapter.myViewHolder>() {

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name_of_room : TextView = itemView.findViewById<TextView>(R.id.roomlist_name_text)
        private val max_current : TextView = itemView.findViewById<TextView>(R.id.roomlist_item_text1)
        private val roomlistcard :  CardView = itemView.findViewById<CardView>(R.id.roomlist_card1)
        fun bind(info : Room_info, context: Context)
        {
            name_of_room.text = info.name
            max_current.text = info.players.count().toString() + "/" +info.playerNum.toString()
            roomlistcard.setOnClickListener({
                mysocket.emit("enter-lobby", info.unique_id)
                Log.d("방입장",info.unique_id)
                Log.d("방입장","보냄")
                mysocket.on("enter-lobby")  { arg ->
                    val jsonObject = JSONObject(arg[0].toString())
                    if(jsonObject.getBoolean("success")) {
                        Log.d("방입장","성공")
                        mysocket.emit("new_player_enter_room",info.unique_id)
                        val intent_to_lobby = Intent(context,Waiting_screen::class.java)
                        intent_to_lobby.run{context.startActivity(this)}
                    }
                    else{
                         Log.d("방입장","실패용")

                    }
                }
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.roomlist_item, parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(dataList[position],context)
    }

}