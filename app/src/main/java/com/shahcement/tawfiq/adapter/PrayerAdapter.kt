package com.shahcement.tawfiq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahcement.tawfiq.databinding.ItemPrayerTimeBinding
import com.shahcement.tawfiq.model.Prayer

class PrayerAdapter(private val models: List<Prayer>) :
    RecyclerView.Adapter<PrayerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPrayerTimeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPrayerTimeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = models[position].title
        holder.binding.tvTime.text = if (models[position].time.contains("am", true)) {
            models[position].time.substringBefore(" AM")
        } else {
            models[position].time.substringBefore(" PM")
        }
        holder.binding.ivPrayer.setImageResource(models[position].image)
    }

    override fun getItemCount(): Int {
        return models.size
    }
}