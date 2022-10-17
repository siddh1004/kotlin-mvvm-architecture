package com.example.nasaious.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaious.databinding.ListItemImageBinding
import com.example.nasaious.domain.model.Article

class ImageItemAdapter(private val onClick: (Article) -> Unit) :
    ListAdapter<Article, ImageItemAdapter.ImageViewHolder>(ImageDiffCallback) {

    class ImageViewHolder(
        private val binding: ListItemImageBinding,
        private val onClick: (Article) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.article?.let { article ->
                    onClick.invoke(article)
                }
            }
        }

        fun bind(newsArticle: Article) {
            binding.apply {
                article = newsArticle
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ListItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }
}

object ImageDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}