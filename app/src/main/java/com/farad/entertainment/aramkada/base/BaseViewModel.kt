package com.farad.entertainment.aramkada.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farad.entertainment.aramkada.app.BaseApp
import com.farad.entertainment.aramkada.data.apiService.ApiConfig
import com.farad.entertainment.aramkada.data.maneger.response.NetworkRequest
import com.farad.entertainment.aramkada.data.maneger.response.onError
import com.farad.entertainment.aramkada.data.maneger.response.onLoading
import com.farad.entertainment.aramkada.data.maneger.response.onSuccess
import com.farad.entertainment.aramkada.utils.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Vector

@Suppress("unused", "PropertyName")
abstract class BaseViewModel : ViewModel(), KoinComponent {

    companion object {
        const val DEFAULT_TAG_REQUEST = "DEFAULT"
    }

    protected val _progressLiveData = MutableLiveData<Event<Boolean>>()
    val progressLiveData: LiveData<Event<Boolean>> get() = _progressLiveData
    private val app: Application by inject()
    private val compositeDisposable = Vector<ApiConfig>()

    protected val _errorLiveData = MutableLiveData<Event<Boolean>>()
    val errorLiveData: LiveData<Event<Boolean>> get() = _errorLiveData

    fun getString(@StringRes id: Int): String {
        return app.getString(id)
    }

    fun disposeAll() {
        compositeDisposable.forEach { apiConfig ->
            apiConfig.job.cancel("dispose $apiConfig")
        }
        compositeDisposable.clear()
    }

    @Suppress("unused")
    fun dispose(apiConfig: ApiConfig) {
        val api = compositeDisposable.firstOrNull { it == apiConfig }
        compositeDisposable.remove(api)
        api?.job?.cancel("dispose $apiConfig")
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun getBaseApp() = app as? BaseApp

    fun <T> callSafeApi(
        requestMethod: suspend () -> NetworkRequest<T>,
        onSuccess: suspend NetworkRequest.Success<T>.() -> Unit = {},
        onStart: suspend () -> Unit = {},
        onError: suspend NetworkRequest.Error<T>.() -> Unit = {},
        onCatch: suspend (String) -> Unit = {},

        ): ApiConfig {

        val apiConfig = ApiConfig.Builder()
            .setJob(Job())
            .build()

        callRequest(
            apiConfig = apiConfig,
            onStart = {
                onStart()
            },
            onRequest = {

                requestMethod()

                    .onSuccess {

                        onSuccess(this)
                    }
                    .onError {
                        onError(this)
                    }


            }, onCatch = {
                onCatch(it.message.toString())
            }


        )

        return apiConfig
    }

    private fun callRequest(
        apiConfig: ApiConfig,
        onStart: suspend () -> Unit,
        onRequest: suspend () -> Unit,
        onCatch: suspend (Throwable) -> Unit
    ) {

        viewModelScope.launch(apiConfig.job) {
            try {
                onStart()
                onRequest()
            } catch (e: Exception) {
                onCatch(e)
            }

        }
    }
}
