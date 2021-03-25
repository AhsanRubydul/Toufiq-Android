package com.shahcement.toufiq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahcement.toufiq.databinding.ItemFooterBinding
import com.shahcement.toufiq.databinding.ItemViewBinding
import com.shahcement.toufiq.model.CommonModel

class CommonAdapter(private val models: List<CommonModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_VIEW = 0
        private const val TYPE_FOOTER = 1
    }

    inner class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    inner class FooterHolder(binding: ItemFooterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_VIEW) {
            val binding = ItemViewBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolder(binding)
        } else {
            val binding = ItemFooterBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            FooterHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.binding.tvTitle.text = models[position].title
        }
    }

    override fun getItemCount(): Int {
        return models.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == models.size) {
            TYPE_FOOTER
        } else {
            TYPE_VIEW
        }
    }
}