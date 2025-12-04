package com.example.newsapp.dispatchers

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ProdCoroutineDispatcherProvider @Inject constructor() : CoroutineDispatcherProvider {

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    
    override val unconfirmed: CoroutineDispatcher
        get() = Dispatchers.Unconfined

}