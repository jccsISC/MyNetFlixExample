package com.jccsisc.appnetflixmodule.common

import java.lang.Exception

sealed class MyResource<out T> {
    class Loading<out T>: MyResource<T>()
    data class Success<out T> (val data: T): MyResource<T>()
    data class Failure(val exception: Exception): MyResource<Nothing>()
}