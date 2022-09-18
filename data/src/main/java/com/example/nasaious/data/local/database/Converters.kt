package com.example.nasaious.data.local.database

import androidx.room.TypeConverter
import com.example.nasaious.data.local.entity.ExclusionEntity
import com.example.nasaious.data.local.entity.OptionEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromOptionValuesList(optionValues: List<OptionEntity?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<OptionEntity?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toOptionValuesList(optionValuesString: String?): List<OptionEntity>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<OptionEntity>?>() {}.type
        return gson.fromJson(optionValuesString, type)
    }
}