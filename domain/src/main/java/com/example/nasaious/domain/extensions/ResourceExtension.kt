package com.example.nasaious.domain.extensions

import com.example.nasaious.domain.model.Resource
import com.example.nasaious.domain.model.Status

fun <T> Resource<T>?.showLoader(): Boolean {
    return when {
        this == null -> true
        status != Status.LOADING -> false
        data is List<*> -> data.isEmpty()
        else -> data == null
    }
}

fun <T> Resource<T>?.showZeroState(): Boolean {
    return when {
        this == null -> false
        status == Status.LOADING -> false
        data is List<*> -> data.isEmpty()
        else -> data == null
    }
}