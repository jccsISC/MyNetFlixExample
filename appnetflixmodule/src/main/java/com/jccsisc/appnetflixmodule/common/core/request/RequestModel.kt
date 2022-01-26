package com.jccsisc.appnetflixmodule.common.core.request

data class RequestModel<B>(
    val requestBody: B? = null,
    var requestUrl: String? = null
)