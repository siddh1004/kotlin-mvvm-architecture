package com.example.nasaious.data.remote.response

import com.example.nasaious.data.local.entity.FacilityEntity
import com.example.nasaious.data.remote.util.RoomMapper

data class FacilityResponse(
    val facility_id: String,
    val name: String,
    val options: List<OptionResponse>
) : RoomMapper<FacilityEntity> {
    override fun mapToRoomEntity() = FacilityEntity(
        facility_id,
        "iranjith4",
        name,
    )
}