package com.example.nasaious.data.remote.api

import androidx.lifecycle.LiveData
import com.example.nasaious.data.remote.response.ApiResponse
import com.example.nasaious.data.remote.response.PropertyResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PropertyApi {
    @GET("{propertyId}/ad-assignment/db")
    fun getProperty(
        @Path("propertyId") propertyId: String?
    ): LiveData<ApiResponse<PropertyResponse>>
}
