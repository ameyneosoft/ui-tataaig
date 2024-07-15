package com.example.tataaig

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MotorAdapter (val list: List<Int>) : RecyclerView.Adapter<MotorAdapter.ViewHolder>() {
    class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
    val group1 : Group = view.findViewById(R.id.group_bottom_text)
        val group2 : Group = view.findViewById(R.id.bottom_progress_group)
        val textView : TextView = view.findViewById(R.id.eligible_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view :View = LayoutInflater.from(parent.context).inflate(R.layout.detailed_card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.group1.visibility = View.GONE
        holder.group2.visibility = View.GONE
        holder.textView.apply {
            setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context,R.drawable.ineligible),null,null,null)
            text = "Not Eligible"
        }
    }
}