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

@BindingAdapter(value = ["optionIcon"], requireAll = false)
fun setOptionIcon(
        imageView: ImageView,
        optionIcon: String?,
) {
    val icon = when (optionIcon) {
        "1" -> R.mipmap.apartment
        "2" -> R.mipmap.condo
        "3" -> R.mipmap.boat
        "4" -> R.mipmap.land
        "5" -> R.mipmap.rooms
        "6" -> R.mipmap.no_room
        "7" -> R.mipmap.swimming
        "8" -> R.mipmap.garden
        else -> R.mipmap.garage
    }
    imageView.setImageResource(icon)
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