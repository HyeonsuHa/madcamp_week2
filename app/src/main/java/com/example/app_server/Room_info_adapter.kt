package com.example.app_server

import Room_info_list
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_server.Mysocket.Companion.mysocket
import com.example.app_server.for_sharedpreference.MyApplication

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
                mysocket.emit("enter-lobby", roomlistcard.id)
                Log.d("방입장","보냄")
                mysocket.on("enter-lobby")  { arg ->

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