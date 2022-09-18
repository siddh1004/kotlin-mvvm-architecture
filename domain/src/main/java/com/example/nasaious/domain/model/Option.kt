package com.example.nasaious.domain.model

data class Option(
        val name: String,
        val icon: String,
        val id: String,
        val exclusion: Exclusion?,
        var isSelected: Boolean = false,
        var isDisabled: Boolean = false,
)