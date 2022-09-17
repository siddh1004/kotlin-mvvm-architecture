package com.example.nasaious.data.remote.response

import com.example.nasaious.data.local.entity.PropertyEntity
import com.example.nasaious.data.local.entity.PropertyWithFacilitiesEntity
import com.example.nasaious.data.remote.util.RoomMapper

data class PropertyResponse(
    val facilities: List<FacilityResponse>?,
) : RoomMapper<PropertyWithFacilitiesEntity> {
    override fun mapToRoomEntity() = PropertyWithFacilitiesEntity(
        property = PropertyEntity("1"),
        facilities?.map { it.mapToRoomEntity() } ?: listOf(),
    )
}