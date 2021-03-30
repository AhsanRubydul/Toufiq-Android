package com.shahcement.tawfiq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahcement.tawfiq.Utils
import com.shahcement.tawfiq.data.db.entity.Ramadan
import com.shahcement.tawfiq.databinding.ItemRamadanTimeBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RamadanAdapter(private val models: List<Ramadan>) :
    RecyclerView.Adapter<RamadanAdapter.ViewHolder>() {

    private val sdf = SimpleDateFormat("EEEE", Locale("bn", "BD"))
    private val sdfDate = SimpleDateFormat("dd-MM-yyyy", Locale("bn", "BD"))

    inner class ViewHolder(val binding: ItemRamadanTimeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRamadanTimeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCount.text = Utils.showNumberInBangla(position + 1)
        holder.binding.tvSahriTime.text = models[position].sahriTime.substringBefore(" AM")
        holder.binding.tvIftarTime.text = models[position].iftarTime.substringBefore(" PM")

        val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date = try {
            format.parse(models[position].date)
        } catch (e: ParseException) {
            null
        }

        if (date != null) {
            holder.binding.tvDay.text = sdf.format(date)
            holder.binding.tvDate.text = sdfDate.format(date)
        } else {
            holder.binding.tvDay.text = models[position].date
            holder.binding.tvDate.text = models[position].date
        }
    }

    override fun getItemCount(): Int {
        return models.size
    }
}