package com.example.nasaious.data.common.di

import com.example.nasaious.data.common.repository.property.PropertyRepositoryImpl
import com.example.nasaious.domain.repository.property.PropertyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideImageRepository(
        imageRepositoryImpl: PropertyRepositoryImpl
    ): PropertyRepository
}