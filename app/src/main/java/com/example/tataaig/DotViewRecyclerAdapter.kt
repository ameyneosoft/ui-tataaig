package com.example.tataaig

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class DotViewRecyclerAdapter (val list: List<Boolean>) : RecyclerView.Adapter<DotViewRecyclerAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView : ImageView = view.findViewById<ImageView>(R.id.dot_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.dot_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(list[position]){
            holder.imageView.setImageResource(R.drawable.active_dot)
        }else{
            holder.imageView.setImageResource(R.drawable.inactive_dot)
        }
    }
}