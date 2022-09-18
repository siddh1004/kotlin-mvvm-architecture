package com.example.nasaious.data.local.entity

import androidx.room.Embedded
import com.example.nasaious.data.remote.util.DomainMapper
import com.example.nasaious.domain.model.Option

data class OptionEntity(
        val name: String,
        val icon: String,
        val id: String,
        @Embedded var exclusion: ExclusionEntity?
) : DomainMapper<Option> {
    override fun mapToDomainModel() = Option(name, icon, id, exclusion?.mapToDomainModel())
}