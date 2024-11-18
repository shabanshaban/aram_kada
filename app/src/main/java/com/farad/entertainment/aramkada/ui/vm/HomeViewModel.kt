package com.farad.entertainment.aramkada.ui.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.farad.entertainment.aramkada.base.BaseViewModel
import com.farad.entertainment.aramkada.data.apiService.repository.HomeRepository
import com.farad.entertainment.aramkada.data.maneger.response.NetworkResponse
import com.farad.entertainment.aramkada.data.model.CategoryItemModel
import com.farad.entertainment.aramkada.data.model.CategoryModel
import com.farad.entertainment.aramkada.data.model.SessionModel
import com.farad.entertainment.aramkada.utils.Event
import com.farad.entertainment.aramkada.utils.combineLiveData

class HomeViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {



    //Categories
    private val _categoriesDataLiveData = MutableLiveData<List<CategoryModel>>()
   private val categoriesDataLiveData: LiveData<List<CategoryModel>> = _categoriesDataLiveData


    //CategoryItemList
    private val _categoryItemListLiveData = MutableLiveData<List<CategoryItemModel>>()
    private val categoryItemListLiveData: LiveData<List<CategoryItemModel>> = _categoryItemListLiveData

    //sessionList
    private val _sessionListLiveData = MutableLiveData<Event<List<SessionModel>>>()
    val sessionListLiveData: LiveData<Event<List<SessionModel>>> = _sessionListLiveData

    val combinePeriodLiveData = combineLiveData(categoriesDataLiveData, categoryItemListLiveData)




        fun getCategoryList() {



        callSafeApi(requestMethod = {
            NetworkResponse(homeRepository.getCategoryList()).generateResponse()
        }
            , onStart = {
                _progressLiveData.postValue(Event(true))
            }
            , onSuccess = {
            data?.let {data->
                _categoriesDataLiveData.postValue(data)
                _progressLiveData.postValue(Event(false))
            }
        }, onError = {

        })
    }

      fun getCategoryItemList(id:Long) {
        callSafeApi(requestMethod = {
            NetworkResponse(homeRepository.getCategoryItemList(id)).generateResponse()
        } , onStart = {
            _progressLiveData.postValue(Event(true))
        },

            onSuccess = {
            _progressLiveData.postValue(Event(false))
            data?.let {data->
                _categoryItemListLiveData.postValue(data)
            }
        },
            onCatch = {

            }
            ,
            onError = {

        })
    }

      fun getSessionList(id:Long) {
        callSafeApi(requestMethod = {
            NetworkResponse(homeRepository.getSessionList(id)).generateResponse()
        }, onStart = {
            _progressLiveData.postValue(Event(true))
        }, onSuccess = {
            data?.let {data->
                _progressLiveData.postValue(Event(false))
                _sessionListLiveData.postValue(Event(data))
            }
        }, onError = {

        })
    }


}