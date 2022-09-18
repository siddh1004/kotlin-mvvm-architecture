package com.example.nasaious.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nasaious.data.local.database.PROPERTY_TABLE_NAME

@Entity(tableName = PROPERTY_TABLE_NAME)
data class PropertyEntity(
        @PrimaryKey
        val property_id: String,
)