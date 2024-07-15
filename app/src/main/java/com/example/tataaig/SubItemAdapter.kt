package com.example.tataaig

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubItemAdapter (val li :List<String>) : RecyclerView.Adapter<SubItemAdapter.ViewHolder>(){
    class ViewHolder (val view : View): RecyclerView.ViewHolder(view){
        var item = view.findViewById<TextView>(R.id.expandedListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return li.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.text = li[position]
    }
}