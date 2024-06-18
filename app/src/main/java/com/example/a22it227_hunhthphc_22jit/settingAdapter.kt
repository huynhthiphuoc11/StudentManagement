package com.example.a22it227_hunhthphc_22jit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class settingAdapter(private val itemList: List<setting_item>) : RecyclerView.Adapter<settingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.item_icon)
        val text: TextView = itemView.findViewById(R.id.item_text)
        val text1: TextView = itemView.findViewById(R.id.item_text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_setting, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.icon.setImageResource(item.iconRes)
        holder.text.text = item.text
        holder.text1.text = item.text1
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
