package com.example.islamiapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamiapp.R

class HadithAdapter(var items : List<String>) : RecyclerView.Adapter<HadithAdapter.HadithViewHolder>() {

    class HadithViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hadith_name : TextView = itemView.findViewById(R.id.hadith_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadithViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hadith, parent, false)
        return HadithAdapter.HadithViewHolder(view)
    }

    override fun onBindViewHolder(holder: HadithViewHolder, position: Int) {
        val item = items.get(position)
        holder.hadith_name.text = item
        holder.hadith_name.setOnClickListener {
            if (onTextClickListener != null)
                onTextClickListener.onItemClick(position, item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface onItemClickListener {
        fun onItemClick(position: Int, hadithName: String)
    }

    private lateinit var onTextClickListener: onItemClickListener

    fun setOnTextClickListener(onTextClick: onItemClickListener) {
        onTextClickListener = onTextClick
    }
}