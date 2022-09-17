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
import com.example.nasaious.screen.imageList.OptionAdapter

class FacilityAdapter(
        val context: Context
) : ListAdapter<Facility, FacilityAdapter.ImageViewHolder>(FacilityDiffCallback) {

    class ImageViewHolder(
            private val binding: ListItemFacilityBinding
    ) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Facility, context: Context) {
            val adapter = OptionAdapter { }
            binding.apply {
                facility = item
                facilitiesOptionsRecyclerView.adapter = adapter
                adapter.submitList(item.options)
                facilitiesOptionsRecyclerView.layoutManager = GridLayoutManager(context, 2)
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
                ListItemFacilityBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
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