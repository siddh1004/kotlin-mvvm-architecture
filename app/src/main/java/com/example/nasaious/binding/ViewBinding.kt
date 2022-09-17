package com.example.nasaious.binding

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.example.nasaious.R
import com.example.nasaious.domain.model.Option

@BindingAdapter(value = ["isVisible"], requireAll = false)
fun setIsVisible(
        view: View,
        isVisible: Boolean,
) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["option"], requireAll = false)
fun setBackground(
        view: LinearLayout,
        option: Option?,
) {
    var backgroundResource =
            if (option != null) {
                if (option.isDisabled) R.drawable.option_shape_disabled
                else if (option.isSelected) R.drawable.option_shape_selected
                else R.drawable.option_shape_default
            } else R.drawable.option_shape_default

    view.setBackgroundResource(backgroundResource)
}
