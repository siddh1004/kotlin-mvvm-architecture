package com.example.nasaious.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nasaious.data.local.database.Facility_TABLE_NAME
import com.example.nasaious.data.remote.util.DomainMapper
import com.example.nasaious.domain.model.Facility

@Entity(tableName = Facility_TABLE_NAME)
data class FacilityEntity(
    @PrimaryKey
    val facility_id: String,
    val property_id: String,
    val name: String,
) : DomainMapper<Facility> {
    override fun mapToDomainModel() = Facility(
        facility_id,
        name,
    )
}