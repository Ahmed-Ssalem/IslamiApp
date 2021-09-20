package com.example.islamiapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamiapp.R

class SuraAdapter(var items: List<String>) : RecyclerView.Adapter<SuraAdapter.SuraViewHolder>() {


    class SuraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var suraName: TextView = itemView.findViewById(R.id.sura_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sura, parent, false)
        return SuraViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SuraViewHolder, position: Int) {
        val item = items.get(position)
        holder.suraName.text = item
        holder.suraName.setOnClickListener {
            if (onTextClickListener != null)
                onTextClickListener.onItemClick(position, item)
        }
    }

    interface onItemClickListener {
        fun onItemClick(position: Int, suraName: String)
    }

    private lateinit var onTextClickListener: onItemClickListener

    fun setOnTextClickListener(onTextClick: onItemClickListener) {
        onTextClickListener = onTextClick
    }
}