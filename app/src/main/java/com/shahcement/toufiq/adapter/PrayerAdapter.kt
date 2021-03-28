package com.shahcement.toufiq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahcement.toufiq.databinding.ItemFooterBinding
import com.shahcement.toufiq.databinding.ItemPrayerTimeBinding
import com.shahcement.toufiq.databinding.ItemViewBinding
import com.shahcement.toufiq.model.CommonModel
import com.shahcement.toufiq.model.Prayer

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
        holder.binding.ivPrayer.setImageResource(models[position].image)
    }

    override fun getItemCount(): Int {
        return models.size
    }
}