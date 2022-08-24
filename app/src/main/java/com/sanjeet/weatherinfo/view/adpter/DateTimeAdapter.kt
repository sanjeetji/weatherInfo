package com.sanjeet.weatherinfo.view.adpter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanjeet.weatherinfo.databinding.DateTimeRowItemBinding
import com.sanjeet.weatherinfo.model.DateTime

class DateTimeAdapter : RecyclerView.Adapter<DateTimeAdapter.MyViewHolder>() {

    var list = mutableListOf<DateTime>()

    fun setContentList(list: MutableList<DateTime>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: DateTimeRowItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DateTimeAdapter.MyViewHolder {
        val binding =
            DateTimeRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateTimeAdapter.MyViewHolder, position: Int) {

        holder.binding.dateTime = this.list[position]
        holder.binding.imgSun.setImageResource(list[position].image)

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}