package com.jccsisc.appnetflixmodule.common.core

import android.util.MalformedJsonException
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.jccsisc.appnetflixmodule.common.core.errorresponse.ErrorResponseEnum
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import kotlin.reflect.KClass

class ValidResponse<R, G>(
    val observer: Observer<GenericResponse<R, String, G>>, val requestData: G,
    val vkClass: KClass<*>
) {
    fun validationMethod(result: Call<R>) {

        result.enqueue(object : Callback<R> {
            override fun onFailure(call: Call<R>, t: Throwable) {
                when (t) {
                    is ConnectException -> observer.onChanged(
                        GenericResponse(
                            StatusRequestEnum.FAILURE,
                            null,
                            ErrorResponseEnum.CONEXION_ERROR.messageError,
                            requestData
                        )
                    )
                    is MalformedJsonException -> observer.onChanged(
                        GenericResponse(
                            StatusRequestEnum.FAILURE,
                            null,
                            ErrorResponseEnum.MALFORMED_JSON.messageError,
                            requestData
                        )
                    )
                }
            }

            override fun onResponse(call: Call<R>, response: Response<R>) {
                when (response.code()) {
                    200 -> {
                        if (response.body() != null) {
                            val gson = Gson()
                            val jsonObject = gson.toJsonTree(response.body()).asJsonObject
                            val myResponse =
                                Gson().fromJson(jsonObject, vkClass.javaObjectType) as R
                            observer.onChanged(
                                GenericResponse(
                                    StatusRequestEnum.SUCCESS,
                                    myResponse,
                                    requestData = requestData
                                )
                            )
                        }
                    }
                    401 -> {
                        observer.onChanged(
                            GenericResponse(
                                StatusRequestEnum.FAILURE,
                                null,
                                validateTypeError(response.code()),
                                requestData
                            )
                        )
                    }
                    404 -> {
                        observer.onChanged(
                            GenericResponse(
                                StatusRequestEnum.FAILURE,
                                null,
                                validateTypeError(response.code()),
                                requestData
                            )
                        )
                    }
                }
            }
        })
    }


    fun validateTypeError(error: Int) = when (error) {
        ErrorResponseEnum.UNAUTHORIZED.errorCode -> ErrorResponseEnum.UNAUTHORIZED.messageError
        ErrorResponseEnum.NOT_FOUND.errorCode -> ErrorResponseEnum.NOT_FOUND.messageError
        else -> "Error al consultar el servicio"
    }
}