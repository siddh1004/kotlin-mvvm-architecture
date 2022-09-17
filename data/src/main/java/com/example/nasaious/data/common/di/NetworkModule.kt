package com.example.nasaious.data.common.di

import android.content.Context
import androidx.annotation.NonNull
import com.example.nasaious.data.BuildConfig
import com.example.nasaious.data.common.utils.Connectivity
import com.example.nasaious.data.common.utils.ConnectivityImpl
import com.example.nasaious.data.common.utils.ContextProvider
import com.example.nasaious.data.common.utils.CoroutineContextProvider
import com.example.nasaious.data.remote.BASE_URL
import com.example.nasaious.data.remote.LiveDataCallAdapterFactory
import com.example.nasaious.data.remote.api.PropertyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkConnectivityHelper(@ApplicationContext context: Context): Connectivity {
        return ConnectivityImpl(context)
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): ContextProvider {
        return CoroutineContextProvider()
    }

    @Singleton
    @Provides
    fun provideClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@NonNull httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideTokenApi(@NonNull retrofit: Retrofit): PropertyApi {
        return retrofit.create(PropertyApi::class.java)
    }


    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
}