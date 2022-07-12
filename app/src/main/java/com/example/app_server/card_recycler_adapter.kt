package com.example.app_server

import Card
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_server.Mysocket.Companion.mysocket
import org.json.JSONObject

class card_recycler_adapter( val context: Context, private val dataList : ArrayList<Card>) :
    RecyclerView.Adapter<card_recycler_adapter.myViewHolder>(){

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textview : TextView = itemView.findViewById<TextView>(R.id.game_text)
        private val cardview : CardView = itemView.findViewById<CardView>(R.id.game_card)
        fun bind(card : Card, context: Context)
        {
            textview.text = card.number.toString()
            cardview.setCardBackgroundColor(Color.parseColor(card.card_color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(dataList[position],context)
    }
}