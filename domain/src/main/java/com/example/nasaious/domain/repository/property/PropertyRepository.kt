package com.example.nasaious.domain.repository.property

import androidx.lifecycle.LiveData
import com.example.nasaious.domain.model.Property
import com.example.nasaious.domain.model.Resource
import com.example.nasaious.domain.model.Result

interface PropertyRepository {
    fun getProperty(propertyId: String): LiveData<Resource<Property>>
}