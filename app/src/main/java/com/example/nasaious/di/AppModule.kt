package com.example.nasaious.di

import android.content.Context
import com.example.nasaious.service.PushNotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providePushNotificationManager(@ApplicationContext context: Context): PushNotificationManager {
        return PushNotificationManager(context)
    }
}