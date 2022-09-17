package com.example.nasaious.domain.extensions

import com.example.nasaious.domain.model.Property
import com.example.nasaious.domain.model.Resource
import com.example.nasaious.domain.model.Status

fun Resource<Property>?.showLoader(): Boolean {
    return if (this != null) {
        status == Status.LOADING && data?.facilities.isNullOrEmpty()
    } else false
}

fun <T> Resource<T>?.showZeroState(): Boolean {
    return when {
        this == null -> false
        status == Status.LOADING -> false
        data is List<*> -> data.isEmpty()
        else -> data == null
    }
}