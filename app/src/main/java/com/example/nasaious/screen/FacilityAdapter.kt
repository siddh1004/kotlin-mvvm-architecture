package com.example.nasaious.screen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaious.databinding.ListItemFacilityBinding
import com.example.nasaious.domain.model.Facility
import com.example.nasaious.domain.model.Option

class FacilityAdapter(
        private val context: Context,
        private val onClick: () -> Unit
) : ListAdapter<Facility, FacilityAdapter.ImageViewHolder>(FacilityDiffCallback) {

    class ImageViewHolder(
            private val binding: ListItemFacilityBinding,
            private val onClick: () -> Unit
    ) :
            RecyclerView.ViewHolder(binding.root) {

        private lateinit var facility: Facility

        fun bind(item: Facility, context: Context) {
            facility = item
            binding.apply {
                facility = item
                val adapter = OptionAdapter(::onOptionClick)
                facilitiesOptionsRecyclerView.adapter = adapter
                adapter.submitList(item.options)
                facilitiesOptionsRecyclerView.layoutManager = GridLayoutManager(context, 2)
                executePendingBindings()
            }
        }

        private fun onOptionClick(option: Option) {
            facility.options?.map { it.isSelected = it.id == option.id }
            onClick.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
                ListItemFacilityBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                ),
                onClick
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image, context)
    }
}

object FacilityDiffCallback : DiffUtil.ItemCallback<Facility>() {
    override fun areItemsTheSame(oldItem: Facility, newItem: Facility): Boolean {
        return oldItem.facility_id == newItem.facility_id
    }

    override fun areContentsTheSame(oldItem: Facility, newItem: Facility): Boolean {
        return oldItem == newItem
    }
}