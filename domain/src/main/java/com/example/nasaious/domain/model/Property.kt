package com.example.nasaious.domain.model

data class Property(
    val facilities: List<Facility>?,
    val exclusions: List<List<Exclusion>>?,
)