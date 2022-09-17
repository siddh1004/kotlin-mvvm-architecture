package com.example.nasaious.screen.imageList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.nasaious.base.Error
import com.example.nasaious.base.Success
import com.example.nasaious.base.ViewModelBase
import com.example.nasaious.domain.model.Property
import com.example.nasaious.domain.model.Resource
import com.example.nasaious.domain.model.onFailure
import com.example.nasaious.domain.model.onSuccess
import com.example.nasaious.domain.repository.property.PropertyRepository
import com.example.nasaious.utils.AbsentLiveData
import javax.inject.Inject

class PropertyViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository
) : ViewModel() {
    private val _propertyId = MutableLiveData<String>()
    val propertyId: LiveData<String>
        get() = _propertyId

    val property: LiveData<Resource<Property>> = _propertyId.switchMap { propertyId ->
        if (propertyId == null) {
            AbsentLiveData.create()
        } else {
            propertyRepository.getProperty(propertyId)
        }
    }

    fun setPropertyId(propertyId: String) {
        if (_propertyId.value != propertyId) {
            _propertyId.value = propertyId
        }
    }

    fun retry() {
        _propertyId.value?.let {
            _propertyId.value = it
        }
    }
}