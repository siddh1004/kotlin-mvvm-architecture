package com.example.nasaious.data.common.di

import android.content.Context
import com.example.nasaious.data.local.dao.NewsDao
import com.example.nasaious.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideNewsDao(db: AppDatabase): NewsDao {
        return db.newsDao()
    }
}