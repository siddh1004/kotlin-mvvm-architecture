package com.example.nasaious.domain.model

import java.util.*

data class Property(
        val propertyId: String,
        val facilities: List<Facility>?,
        val lastFetchTime: Date?
)