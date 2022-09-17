package com.example.nasaious.data.common.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface ContextProvider {
    val main: CoroutineContext
    val io: CoroutineContext
    val default: CoroutineContext
}

class CoroutineContextProvider : ContextProvider {
    override val main: CoroutineContext by lazy { Dispatchers.Main }
    override val io: CoroutineContext by lazy { Dispatchers.IO }
    override val default: CoroutineContext by lazy { Dispatchers.Default }
}

class TestCoroutineContextProvider : ContextProvider {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
    override val default: CoroutineContext = Dispatchers.Unconfined
}