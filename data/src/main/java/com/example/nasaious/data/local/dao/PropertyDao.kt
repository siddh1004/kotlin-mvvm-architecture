package com.example.nasaious.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nasaious.data.local.database.PROPERTY_TABLE_NAME
import com.example.nasaious.data.local.entity.FacilityEntity
import com.example.nasaious.data.local.entity.PropertyEntity
import com.example.nasaious.data.local.entity.PropertyWithFacilitiesEntity

@Dao
abstract class PropertyDao {
    @Transaction
    @Query("SELECT * FROM $PROPERTY_TABLE_NAME")
    abstract fun getProperties(): LiveData<PropertyWithFacilitiesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePropertyInfo(propertyWithFacilitiesEntity: PropertyWithFacilitiesEntity) {
        saveProperty(propertyWithFacilitiesEntity.property)
        saveFacilities(propertyWithFacilitiesEntity.facilities)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun saveProperty(summary: PropertyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun saveFacilities(summary: List<FacilityEntity>)

    @Query("DELETE FROM $PROPERTY_TABLE_NAME")
    abstract fun nukeTable()
}