package com.example.tataaig

import NavigationMenuGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tataaig.databinding.MenuListBinding

class MenuListAdapter(val menuList: List<NavigationMenuGroup>) :
    RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {
    inner class ViewHolder(val bind: MenuListBinding) : RecyclerView.ViewHolder(bind.root) {
        init {

                    bind.menuItem.setOnClickListener {
                        if (adapterPosition != RecyclerView.NO_POSITION && menuList[adapterPosition].items.isNotEmpty()) {
                            menuList[adapterPosition].isExpanable =
                                !menuList[adapterPosition].isExpanable
                            notifyItemChanged(adapterPosition)
                        }
                    }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MenuListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val navigationMenuGroup: NavigationMenuGroup = menuList[position]
        holder.bind.menuItem.apply {
            text = navigationMenuGroup.text
            val drawEnd = if (navigationMenuGroup.items?.size != 0) if(navigationMenuGroup.isExpanable)  ContextCompat.getDrawable(
                context,
                R.drawable.ic_up
            )  else ContextCompat.getDrawable(
                context,
                R.drawable.ic_down
            ) else null
            setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(context, navigationMenuGroup.icon),
                null,
                drawEnd,
                null
            )


        }

        val subItemAdapter = SubItemAdapter(menuList[position].items)
        holder.bind.submenuRecycler.apply {
            adapter = subItemAdapter
            layoutManager = LinearLayoutManager(holder.itemView.context)
            if (navigationMenuGroup.isExpanable) {
                visibility = View.VISIBLE
            } else {
                visibility = View.GONE
            }
        }

    }
}