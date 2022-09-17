package com.example.nasaious.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
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
        "6" -> R.mipmap.rooms
        "7" -> R.mipmap.no_room
        "10" -> R.mipmap.swimming
        "11" -> R.mipmap.garden
        else -> R.mipmap.garage
    }
    imageView.setImageResource(icon)
}