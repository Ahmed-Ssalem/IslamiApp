package com.example.islamiapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamiapp.R

class SingleHadithAdapter(var items: List<String>) : RecyclerView.Adapter<SingleHadithAdapter.hadithsViewHolder>() {


    class hadithsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hadithText: TextView = itemView.findViewById(R.id.single_hadith_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hadithsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_single_hadith, parent, false)
        return hadithsViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: hadithsViewHolder, position: Int) {
        val item = items[position]
        holder.hadithText.text = item
    }
}