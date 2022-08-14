package com.example.artnewsapplicationtorelease.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.artnewsapplicationtorelease.R

class DaysAdapter(ctx: Context, private val days: ArrayList<String>) :
    RecyclerView.Adapter<DaysAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text_day: TextView = view.findViewById<View>(R.id.day_of_week_text) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.day_of_week, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: String = days[position]
        holder.text_day.text = item
    }

    override fun getItemCount(): Int {
        return days.size
    }


}