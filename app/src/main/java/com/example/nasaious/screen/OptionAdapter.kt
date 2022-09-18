package com.example.nasaious.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaious.databinding.ListItemOptionBinding
import com.example.nasaious.domain.model.Option

class OptionAdapter(
        private val onClick: ((Option) -> Unit)
) :
        ListAdapter<Option, OptionAdapter.ImageViewHolder>(OptionDiffCallback) {

    class ImageViewHolder(
            private val binding: ListItemOptionBinding,
    ) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Option, onClick: (Option) -> Unit) {
            binding.setClickListener {
                binding.option?.let { option ->
                    onClick.invoke(option)
                }
            }
            binding.apply {
                option = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
                ListItemOptionBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image, onClick)
    }
}

object OptionDiffCallback : DiffUtil.ItemCallback<Option>() {
    override fun areItemsTheSame(oldItem: Option, newItem: Option): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Option, newItem: Option): Boolean {
        return oldItem == newItem
    }
}