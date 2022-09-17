package com.example.nasaious.data.remote.api

import com.example.nasaious.data.common.utils.ContextProvider
import com.example.nasaious.data.remote.GENERAL_NETWORK_ERROR
import com.example.nasaious.data.remote.util.getData
import com.example.nasaious.domain.model.Result
import com.example.nasaious.domain.model.Failure
import com.example.nasaious.domain.model.HttpError
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

abstract class ApiCaller {

    @Inject
    lateinit var contextProvider: ContextProvider

    suspend fun <T : Any, R : Any> call(
        apiCall: suspend () -> Response<T>,
        onSuccess: suspend (T) -> Result<R>,
    ): Result<R> = withContext(contextProvider.io) {
        try {
            apiCall().getData(onSuccess) {
                Failure(it)
            }
        } catch (e: Exception) {
            Failure(HttpError(e.message ?: GENERAL_NETWORK_ERROR))
        }
    }
}