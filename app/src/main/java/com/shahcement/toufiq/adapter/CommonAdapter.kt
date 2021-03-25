package com.shahcement.toufiq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahcement.toufiq.databinding.ItemRecyclerBinding
import com.shahcement.toufiq.model.CommonModel

class CommonAdapter(private val models: List<CommonModel>) :
    RecyclerView.Adapter<CommonAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = models[position].title
    }

    override fun getItemCount(): Int {
        return models.size
    }
}