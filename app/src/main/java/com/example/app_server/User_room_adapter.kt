package com.example.app_server.com.example.app_server

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_server.R
import com.example.app_server.player

class User_room_adapter (private val context: Context, private val dataList: ArrayList<player>) :
    RecyclerView.Adapter<User_room_adapter.myViewHolder2>()
{

    inner class myViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val player_nickname : TextView = itemView.findViewById<TextView>(R.id.user_nickname)
        private val player_wininfo : TextView = itemView.findViewById<TextView>(R.id.user_winrate)
        private val profile: ImageView=itemView.findViewById(R.id.profile_photo)
        private val ready_profile: ImageView=itemView.findViewById(R.id.Isready)
        fun bind(player : player, context: Context)
        {
            player_nickname.text = player.nickname
            player_wininfo.text = String.format("%d전 %d승 ",player.round,player.win)
            Glide.with(itemView).load(player.profile).into(profile)
            ready_profile
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder2 {
        val view = LayoutInflater.from(context).inflate(R.layout.player_box_item,parent,false)
        return myViewHolder2(view)
    }

    override fun onBindViewHolder(holder: myViewHolder2, position: Int) {
        holder.bind(dataList[position],context)

    }
}