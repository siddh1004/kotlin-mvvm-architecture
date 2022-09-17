package com.example.nasaious.data.remote.response

import com.example.nasaious.data.local.entity.ExclusionEntity
import com.example.nasaious.data.remote.util.RoomMapper

data class ExclusionResponse(
    val facility_id: String,
    val options_id: String
) : RoomMapper<ExclusionEntity> {
    override fun mapToRoomEntity() = ExclusionEntity(facility_id, options_id)
}