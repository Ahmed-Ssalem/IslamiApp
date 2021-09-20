package com.example.islamiapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamiapp.R

class AyaAdapter(var items: List<String>) : RecyclerView.Adapter<AyaAdapter.ayatViewHolder>() {


    class ayatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ayatText: TextView = itemView.findViewById(R.id.aya_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ayatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_aya, parent, false)
        return ayatViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ayatViewHolder, position: Int) {
        val item = items.get(position)
        holder.ayatText.text = item
    }
}