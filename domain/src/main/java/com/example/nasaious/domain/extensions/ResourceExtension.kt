package com.example.nasaious.domain.extensions

import com.example.nasaious.domain.model.Article
import com.example.nasaious.domain.model.Resource
import com.example.nasaious.domain.model.Status

fun Resource<List<Article>>?.showLoader(): Boolean {
    return if (this != null) {
        status == Status.LOADING && data?.isNotEmpty() == true
    } else false
}
