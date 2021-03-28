package com.shahcement.tawfiq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahcement.tawfiq.data.db.entity.Ramadan
import com.shahcement.tawfiq.databinding.ItemPrayerTimeBinding

class RamadanAdapter(private val models: List<Ramadan>) :
    RecyclerView.Adapter<RamadanAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPrayerTimeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPrayerTimeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = models[position].date
//        holder.binding.tvTime.text = models[position].time
//        holder.binding.ivPrayer.setImageResource(models[position].image)
    }

    override fun getItemCount(): Int {
        return models.size
    }
}