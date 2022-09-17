package com.example.nasaious.data.remote.util

import android.util.Log
import com.example.nasaious.data.remote.GENERAL_NETWORK_ERROR
import com.example.nasaious.data.remote.response.ErrorResponse
import com.example.nasaious.domain.model.Result
import com.example.nasaious.domain.model.HttpError
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) = apply {
    if (!isSuccessful) errorBody()?.run { action(getHttpError()) }
}

fun <T : Any> Response<T>.getHttpError(): HttpError {
    val msg = errorBody()?.string()
    val errorMsg = if (msg.isNullOrEmpty()) {
        message()
    } else {
        try {
            val converter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val jsonAdapter = converter.adapter(ErrorResponse::class.java)
            val jsonMsg = jsonAdapter.fromJson(msg)
            if (!jsonMsg?.error.isNullOrEmpty()) jsonMsg?.error else jsonMsg?.message
        } catch (ex: Exception) {
            Log.w("Error parsing:%s", msg)
            null
        }
    }
    return HttpError(errorMsg ?: "Oops something went wrong.\nPlease try again.")
}

suspend fun <T : Any, R : Any> Response<T>.getData(
    successAction: suspend (T) -> Result<R>,
    failureAction: suspend (HttpError) -> Result<R>,
): Result<R> {
    try {
        onSuccess {
            return successAction(it)
        }
        onFailure { return failureAction(it) }
        return failureAction(HttpError(GENERAL_NETWORK_ERROR))
    } catch (e: Exception) {
        return failureAction(HttpError(e.message ?: GENERAL_NETWORK_ERROR))
    }
}