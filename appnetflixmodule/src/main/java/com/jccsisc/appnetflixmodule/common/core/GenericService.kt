package com.jccsisc.appnetflixmodule.common.core

import retrofit2.Call
import retrofit2.http.*

interface GenericService {
    @GET
    fun serviceResponseGetNoBody(
        @Url url: String,
        @Query("api_key") api_key: String
    ): Call<Any>
}