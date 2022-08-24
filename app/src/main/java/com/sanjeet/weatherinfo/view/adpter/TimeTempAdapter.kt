package com.sanjeet.weatherinfo.view.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanjeet.weatherinfo.databinding.TimeTemRowItemBinding
import com.sanjeet.weatherinfo.model.TimeTemp

class TimeTempAdapter : RecyclerView.Adapter<TimeTempAdapter.MyViewHolder>() {

    var list = mutableListOf<TimeTemp>()

    fun setContentList(list: MutableList<TimeTemp>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: TimeTemRowItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TimeTempAdapter.MyViewHolder {
        val binding =
            TimeTemRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeTempAdapter.MyViewHolder, position: Int) {

        holder.binding.timeTemp = this.list[position]
        holder.binding.imgSun.setImageResource(list[position].image)

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}