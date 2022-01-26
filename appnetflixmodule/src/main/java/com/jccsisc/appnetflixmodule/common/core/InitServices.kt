package com.jccsisc.appnetflixmodule.common.core

import com.jccsisc.appnetflixmodule.common.core.request.RestEngine

class InitServices<M, R>(baseUrl: String? = null) {
    var userService: GenericService = RestEngine.getRestEngine(baseUrl).create(GenericService::class.java)
    fun getExecuteServiceMovies(endPoint: String, api_key: String) = userService.serviceResponseGetNoBody(endPoint, api_key) as R
}