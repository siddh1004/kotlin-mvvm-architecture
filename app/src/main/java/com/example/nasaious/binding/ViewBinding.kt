package com.example.nasaious.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["isVisible"], requireAll = false)
fun setIsVisible(
    view: View,
    isVisible: Boolean,
) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}
