@file:Suppress("unused")

package com.farad.entertainment.aramkada.data.maneger.response

suspend inline fun <T> NetworkRequest<T>.onSuccess(
    crossinline onResult: suspend NetworkRequest.Success<T>.() -> Unit,
): NetworkRequest<T> {
    if (this is NetworkRequest.Success) {
        onResult(this)
    }
    return this
}

suspend inline fun <T> NetworkRequest<T>.onLoading(
    crossinline onResult: suspend NetworkRequest.Loading<T>.() -> Unit,
): NetworkRequest<T> {
    if (this is NetworkRequest.Loading) {
        onResult(this)
    }
    return this
}



suspend inline fun <T> NetworkRequest<T>.onError(
    crossinline onResult: suspend NetworkRequest.Error<T>.() -> Unit,
): NetworkRequest<T> {
    if (this is NetworkRequest.Error) {
        onResult(this)
    }
    return this
}




