package com.example.nasaious.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PropertyWithFacilitiesEntity(
    @Embedded val property: PropertyEntity,
    @Relation(
        parentColumn = "property_id",
        entityColumn = "property_id"
    )
    val facilities: List<FacilityEntity>
)