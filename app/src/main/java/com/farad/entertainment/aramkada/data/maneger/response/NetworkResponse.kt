package com.farad.entertainment.aramkada.data.maneger.response

import com.farad.entertainment.aramkada.data.maneger.response.exception.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

open class NetworkResponse<T>(private val response: Response<T>) {


    fun generateResponse(): NetworkRequest<T> {
        return when {

            response.code() == 401 -> NetworkRequest.Error("You are not authorized")
            response.code() != 401 &&
                    response.code() != 422 &&
                    response.isSuccessful.not() -> NetworkRequest.Error("error code ${response.code()}"
            )

            response.code() == 422 -> {
                var errorMessage = ""
                if (response.errorBody() != null) {
                    val errorResponse = Gson().fromJson(
                        response.errorBody()?.charStream(),
                        ErrorResponse::class.java
                    )
                    val errors = errorResponse.errors
                    errors?.forEach { (_, fieldError) ->
                        errorMessage = fieldError.joinToString()
                    }
                }
                NetworkRequest.Error(errorMessage)
            }

            response.code() == 500 -> NetworkRequest.Error("Try again")

            response.isSuccessful -> {
                NetworkRequest.Success(response.body()!!)
            }

            else -> NetworkRequest.Error(response.message())
        }
    }
}