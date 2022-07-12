package com.example.app_server

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class message_recycler_adapter(private val context: Context, private val dataList: ArrayList<message>) :
        RecyclerView.Adapter<message_recycler_adapter.myViewHolder>()
{
            inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                private val systemmessage : TextView = itemView.findViewById<TextView>(R.id.system_message)

                fun bind(mes : message, context: Context)
                {
                    systemmessage.text = mes.sentence
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.system_message_item, parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(dataList[position],context)
    }
        }
