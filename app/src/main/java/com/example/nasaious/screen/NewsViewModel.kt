package com.example.nasaious.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.nasaious.base.ViewModelBase
import com.example.nasaious.domain.model.Article
import com.example.nasaious.domain.model.Resource
import com.example.nasaious.domain.repository.news.NewsRepository
import com.example.nasaious.utils.AbsentLiveData
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModelBase<List<Article>>() {
    private val _country = MutableLiveData<String>()
    val country: LiveData<String>
        get() = _country

    val property: LiveData<Resource<List<Article>>> = _country.switchMap { country ->
        if (country == null) {
            AbsentLiveData.create()
        } else {
            newsRepository.getNewsList(country)
        }
    }

    fun setCountry(country: String) {
        if (_country.value != country) {
            _country.value = country
        }
    }

    fun retry() {
        _country.value?.let {
            _country.value = it
        }
    }

    fun getNews(title: String) = newsRepository.getNews(title)
}