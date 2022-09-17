package com.example.nasaious.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "error") val error: String?,
    @Json(name = "message") val message: String?,
)