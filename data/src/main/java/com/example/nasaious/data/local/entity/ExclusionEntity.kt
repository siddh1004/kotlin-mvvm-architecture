package com.example.nasaious.data.local.entity

import com.example.nasaious.data.remote.util.DomainMapper
import com.example.nasaious.domain.model.Exclusion

data class ExclusionEntity(
    val facility_id: String,
    val options_id: String,
) : DomainMapper<Exclusion> {
    override fun mapToDomainModel() = Exclusion(facility_id, options_id)
}