package com.example.nasaious.domain.model

data class Property(
        val propertyId: String,
        val facilities: List<Facility>?,
)