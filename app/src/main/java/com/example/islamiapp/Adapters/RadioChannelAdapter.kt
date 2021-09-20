package com.example.islamiapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamiapp.R
import com.example.islamiapp.api.RadiosItem

class RadioChannelAdapter() : RecyclerView.Adapter<RadioChannelAdapter.RadioChannelViewHolder>() {

    var channels: MutableList<RadiosItem> ?= null

    class RadioChannelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var radioTitle: TextView = itemView.findViewById(R.id.radio_title)
        var playIc: ImageView = itemView.findViewById(R.id.play_ic)
        var stopIc: ImageView = itemView.findViewById(R.id.stop_ic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioChannelViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_radio_channel, parent, false)
        return RadioChannelViewHolder(view)
    }

    override fun onBindViewHolder(holder: RadioChannelViewHolder, position: Int) {
        var channel = channels?.get(position)
        holder.radioTitle.setText(channel?.name)
        if (onPlayClickListener != null) {
            holder.playIc.setOnClickListener {
                onPlayClickListener?.onItemClick(position, channel!!)
            }
        }
        if (onStopClickListener != null) {
            holder.stopIc.setOnClickListener {
                onStopClickListener?.onItemClick(position, channel!!)
            }
        }

    }

    override fun getItemCount(): Int {
        return channels?.size ?: 0
    }


    interface onItemClickListener {
        fun onItemClick(position: Int, channel: RadiosItem)
    }

    private var onPlayClickListener: onItemClickListener? = null

    private var onStopClickListener: onItemClickListener? = null

    fun setOnPlayClickListener(onItemClick: onItemClickListener) {
        onPlayClickListener = onItemClick
    }

    fun setOnStopClickListener(onItemClick: onItemClickListener) {
        onStopClickListener = onItemClick
    }

    fun changeData(radioChannel: MutableList<RadiosItem>?) {
        channels = radioChannel
        notifyDataSetChanged()
    }


}