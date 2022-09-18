package com.example.nasaious.data.remote.response

import com.example.nasaious.data.local.entity.PropertyEntity
import com.example.nasaious.data.local.entity.PropertyWithFacilitiesEntity
import com.example.nasaious.data.remote.util.RoomMapper

data class PropertyResponse(
        val facilities: List<FacilityResponse>?,
        val exclusions: List<List<ExclusionResponse>>?,
) : RoomMapper<PropertyWithFacilitiesEntity> {
    override fun mapToRoomEntity() = PropertyWithFacilitiesEntity(
            property = PropertyEntity(
                    "iranjith4",
            ),
            facilities?.map {
                addExclusion(it, exclusions)
                it.mapToRoomEntity()
            } ?: listOf(),
    )

    private fun addExclusion(facility: FacilityResponse, exclusions: List<List<ExclusionResponse>>?) {
        exclusions?.forEach { exclusionList ->
            if (exclusionList[0].facility_id == facility.facility_id) {
                facility.options.map { option ->
                    if (option.id == exclusionList[0].options_id) {
                        option.exclusion = ExclusionResponse(
                                exclusionList[1].facility_id,
                                exclusionList[1].options_id
                        )
                    }
                }
            }
        }
    }
}