package com.example.nasaious.data.common.repository.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.nasaious.data.local.dao.NewsDao
import com.example.nasaious.data.remote.API_KEY
import com.example.nasaious.data.remote.api.ApiCaller
import com.example.nasaious.data.remote.api.NewsApi
import com.example.nasaious.data.remote.response.ApiResponse
import com.example.nasaious.data.remote.response.NewsResponse
import com.example.nasaious.domain.model.Article
import com.example.nasaious.domain.model.Resource
import com.example.nasaious.domain.repository.news.NewsRepository
import com.example.nasaious.domain.util.AppExecutors
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    val appExecutors: AppExecutors,
    private val newsDao: NewsDao,
    private val newsApi: NewsApi
) : NewsRepository, ApiCaller() {
    override fun getNewsList(country: String): LiveData<Resource<List<Article>>> {
        return object :
            NetworkBoundResource<List<Article>, NewsResponse>(appExecutors) {
            override fun saveCallResult(item: NewsResponse) {
                saveNewsToDb(item)
            }

            override fun shouldFetch(data: List<Article>?) = true

            override fun loadFromDb(): LiveData<List<Article>> {
                return Transformations.map(newsDao.getArticles()) { articles ->
                    articles.map { it.mapToDomainModel() }
                }
            }

            override fun createCall(): LiveData<ApiResponse<NewsResponse>> {
                return newsApi.getTopHeadlines(country, API_KEY)
            }
        }.asLiveData()
    }

    override fun getNews(title: String): LiveData<Article> {
        return Transformations.map(newsDao.getArticle(title)) { it.mapToDomainModel() }
    }

    private fun saveNewsToDb(newsResponse: NewsResponse) {
        if (!newsResponse.articles.isNullOrEmpty()) {
            newsDao.nukeTable()
            newsDao.saveArticles(newsResponse.articles.map { it.mapToRoomEntity() })
        }
    }
}