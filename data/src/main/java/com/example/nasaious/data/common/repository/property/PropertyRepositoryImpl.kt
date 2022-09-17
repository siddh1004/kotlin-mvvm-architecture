package com.example.nasaious.data.common.repository.property

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.nasaious.data.local.dao.PropertyDao
import com.example.nasaious.data.remote.api.ApiCaller
import com.example.nasaious.data.remote.api.PropertyApi
import com.example.nasaious.data.remote.response.ApiResponse
import com.example.nasaious.data.remote.response.PropertyResponse
import com.example.nasaious.domain.model.*
import com.example.nasaious.domain.repository.property.PropertyRepository
import com.example.nasaious.domain.util.AppExecutors
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
        val appExecutors: AppExecutors,
        private val propertyDao: PropertyDao,
        private val propertyApi: PropertyApi
) : PropertyRepository, ApiCaller() {
    override fun getProperty(propertyId: String): LiveData<Resource<Property>> {
        return object :
                NetworkBoundResource<Property, PropertyResponse>(appExecutors) {
            override fun saveCallResult(item: PropertyResponse) {
                savePropertyToDb(item)
            }

            override fun shouldFetch(data: Property?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<Property> {
                return Transformations.map(propertyDao.getProperties()) { properties ->
                    Property(
                            propertyId = propertyId,
                            facilities = properties.facilities.map { it.mapToDomainModel() },
                            listOf()
                    )
                }
            }

            override fun createCall(): LiveData<ApiResponse<PropertyResponse>> {
                return propertyApi.getProperty(propertyId)
            }
        }.asLiveData()
    }

    private fun savePropertyToDb(propertyResponse: PropertyResponse) {
        if (!propertyResponse.facilities.isNullOrEmpty()) {
            propertyDao.nukeTable()
            propertyDao.savePropertyInfo(propertyResponse.mapToRoomEntity())
        }
    }
}