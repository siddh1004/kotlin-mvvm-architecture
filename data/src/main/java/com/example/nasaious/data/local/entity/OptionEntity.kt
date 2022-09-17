package com.example.nasaious.data.local.entity

import com.example.nasaious.data.remote.util.DomainMapper
import com.example.nasaious.domain.model.Option

data class OptionEntity(
    val name: String,
    val icon: String,
    val id: String
) : DomainMapper<Option> {
    override fun mapToDomainModel() = Option(name, icon, id)
}