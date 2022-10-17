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

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    val news: LiveData<Resource<List<Article>>> = _country.switchMap { country ->
        if (country == null) {
            AbsentLiveData.create()
        } else {
            newsRepository.getNewsList(country)
        }
    }

    val newsDetail: LiveData<Article> = _title.switchMap { title ->
        if (title == null) {
            AbsentLiveData.create()
        } else {
            newsRepository.getNews(title)
        }
    }

    fun setCountry(country: String) {
        if (_country.value != country) {
            _country.value = country
        }
    }

    fun setTitle(title: String) {
        if (_title.value != title) {
            _title.value = title
        }
    }

    fun retry() {
        _country.value?.let {
            _country.value = it
        }
    }
}