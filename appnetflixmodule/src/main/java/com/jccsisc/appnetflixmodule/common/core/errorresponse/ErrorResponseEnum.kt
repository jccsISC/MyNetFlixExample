package com.jccsisc.appnetflixmodule.common.core.errorresponse

enum class ErrorResponseEnum(val messageError: String, val errorCode: Int) {
    CONEXION_ERROR("Tu conexion ha fallado", 1012),
    NOT_FOUND("Url no encontrada", 404),
    UNAUTHORIZED("No se pudo ejecutar la petición: Autenticación negada", 401),
    MALFORMED_JSON("El modelo de respuesta es incorrecto",  1000)
}