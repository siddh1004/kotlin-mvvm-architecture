package com.example.nasaious.data.remote.response

import com.example.nasaious.data.local.entity.OptionEntity
import com.example.nasaious.data.remote.util.RoomMapper

data class OptionResponse(
    val name: String,
    val icon: String,
    val id: String
) : RoomMapper<OptionEntity> {
    override fun mapToRoomEntity() = OptionEntity(
        name, icon, id
    )
}