package com.example.nasaious.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.nasaious.domain.model.*
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

    fun updateExclusionOnClick() {
        resetDisability()

        property.value?.data?.facilities?.forEach { facility ->
            facility.options?.forEach { option ->
                if (option.isSelected && option.exclusion != null) {
                    updateOptionDisability(requireNotNull(option.exclusion))
                }
            }
        }
    }

    private fun updateOptionDisability(exclusion: Exclusion) {
        val facility = property.value?.data?.facilities?.firstOrNull {
            it.facility_id == exclusion.facility_id
        }
        val option = facility?.options?.firstOrNull {
            it.id == exclusion.options_id
        }
        option?.isDisabled = true
    }

    private fun resetDisability() {
        property.value?.data?.facilities?.map { facility ->
            facility.options?.map { option ->
                option.isDisabled = false
            }
        }
    }
}