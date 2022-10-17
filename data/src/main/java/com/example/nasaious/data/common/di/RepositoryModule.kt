package com.example.nasaious.data.common.di

import com.example.nasaious.data.common.repository.news.NewsRepositoryImpl
import com.example.nasaious.domain.repository.news.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideImageRepository(
        imageRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}