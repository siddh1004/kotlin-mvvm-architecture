package com.example.nasaious.data.remote.api

import androidx.lifecycle.LiveData
import com.example.nasaious.data.remote.response.ApiResponse
import com.example.nasaious.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): LiveData<ApiResponse<NewsResponse>>
}
