package com.example.nasaious.screen.imageList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaious.databinding.ListItemOptionBinding
import com.example.nasaious.domain.model.Option

private var selectedPosition = -1

class OptionAdapter(private val onClick: (Option) -> Unit) :
        ListAdapter<Option, OptionAdapter.ImageViewHolder>(OptionDiffCallback) {

    class ImageViewHolder(
            private val binding: ListItemOptionBinding,
            private val onClick: (Option) -> Unit
    ) :
            RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                selectedPosition = adapterPosition
                binding.option?.let { option ->
                    onClick.invoke(option)
                }
            }
        }

        fun bind(item: Option) {
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
        ) {
            notifyDataSetChanged()
            onClick
        }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        image.isSelected = selectedPosition == position
        holder.bind(image)
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