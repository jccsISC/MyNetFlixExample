package com.jccsisc.appnetflixmodule.common.core.response

import androidx.lifecycle.MutableLiveData
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum

data class GenericResponse<S, E, T>(
    val statusRequest: StatusRequestEnum = StatusRequestEnum.NONE,
    val successData: S? = null,
    val errorData: E? = null,
    val requestData: T? = null
):MutableLiveData<GenericResponse<S,E,T>>()