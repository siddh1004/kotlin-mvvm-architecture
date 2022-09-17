package com.example.nasaious.data.remote.response

data class BaseResponse<T>(
    val status: String? = "",
    val success: Boolean? = false,
    val error: String? = "",
    val data: T? = null
)