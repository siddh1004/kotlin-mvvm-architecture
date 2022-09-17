package com.example.nasaious.binding

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.nasaious.R

@BindingAdapter(value = ["imageUrl", "placeholder", "size"], requireAll = false)
fun loadImageUrl(
    imageView: ImageView,
    imageUrl: String?,
    placeholder: Drawable?,
    size: Float
) {
    try {
        if (imageUrl != null) {
            loadUrl(imageView, imageUrl, placeholder, size.toInt())
        } else {
            imageView.setImageDrawable(
                placeholder ?: ColorDrawable(Color.GRAY)
            )
        }
    } catch (ex: Exception) {
        imageView.setImageDrawable(imageView.resources.getDrawable(R.drawable.ic_broken_image))
    }
}

private fun loadUrl(
    imageView: ImageView,
    imageUrl: String?,
    placeholder: Drawable?,
    size: Int
) {
    Glide
        .with(imageView.context)
        .asBitmap()
        .load(imageUrl)
        .dontAnimate()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_broken_image)
        .placeholder(placeholder ?: ColorDrawable(Color.GRAY))
        .override(size)
        .into(imageView)
}