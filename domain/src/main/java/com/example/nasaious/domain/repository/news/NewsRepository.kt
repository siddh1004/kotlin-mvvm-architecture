package com.example.nasaious.domain.repository.news

import androidx.lifecycle.LiveData
import com.example.nasaious.domain.model.Article
import com.example.nasaious.domain.model.Resource

interface NewsRepository {
    fun getNewsList(country: String): LiveData<Resource<List<Article>>>
    fun getNews(title: String): LiveData<Article>
}