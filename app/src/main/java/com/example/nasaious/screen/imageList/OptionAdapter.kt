//package com.example.nasaious.screen.imageList
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.nasaious.databinding.ListItemImageBinding
//import com.example.nasaious.domain.model.Image
//import com.example.nasaious.domain.model.Option
//
//class OptionAdapter(private val onClick: (Option) -> Unit) :
//    ListAdapter<Option, OptionAdapter.ImageViewHolder>(OptionDiffCallback) {
//
//    class ImageViewHolder(
//        private val binding: ListItemImageBinding,
//        private val onClick: (Option) -> Unit
//    ) :
//        RecyclerView.ViewHolder(binding.root) {
//        init {
//            binding.setClickListener {
//                binding.nasaImage?.let { image ->
//                    onClick.invoke(image)
//                }
//            }
//        }
//
//        fun bind(image: Option) {
//            binding.apply {
//                nasaImage = image
//                executePendingBindings()
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
//        return ImageViewHolder(
//            ListItemImageBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            ),
//            onClick
//        )
//    }
//
//    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        val image = getItem(position)
//        holder.bind(image)
//    }
//}
//
//object OptionDiffCallback : DiffUtil.ItemCallback<Option>() {
//    override fun areItemsTheSame(oldItem: Option, newItem: Option): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: Option, newItem: Option): Boolean {
//        return oldItem == newItem
//    }
//}