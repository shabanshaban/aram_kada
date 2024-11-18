package com.farad.entertainment.aramkada.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.farad.entertainment.aramkada.base.BaseViewModel
import com.farad.entertainment.aramkada.data.apiService.repository.HomeRepository
import com.farad.entertainment.aramkada.data.maneger.response.NetworkResponse
import com.farad.entertainment.aramkada.data.model.CategoryItemModel
import com.farad.entertainment.aramkada.data.model.CategoryModel
import com.farad.entertainment.aramkada.data.model.SessionModel
import com.farad.entertainment.aramkada.utils.Event
import com.farad.entertainment.aramkada.utils.combineLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {


   /* //Categories
    private val _categoriesDataLiveData = MutableLiveData<List<CategoryModel>>()
    private val categoriesDataLiveData: LiveData<List<CategoryModel>> = _categoriesDataLiveData


    //CategoryItemList
    private val _categoryItemListLiveData = MutableLiveData<List<CategoryItemModel>>()
    private val categoryItemListLiveData: LiveData<List<CategoryItemModel>> =
        _categoryItemListLiveData

    //sessionList
    private val _sessionListLiveData = MutableLiveData<Event<List<SessionModel>>>()
    val sessionListLiveData: LiveData<Event<List<SessionModel>>> = _sessionListLiveData

    val combinePeriodLiveData = combineLiveData(categoriesDataLiveData, categoryItemListLiveData)
*/
    private val _combinedCategoryData = MutableStateFlow<Pair<List<CategoryModel>, List<CategoryItemModel>>>(Pair(arrayListOf(), arrayListOf()))
    val combinedCategoryData: StateFlow<Pair<List<CategoryModel>, List<CategoryItemModel>>> = _combinedCategoryData

    private val listCategory = getCategoryFlow()


    private fun getCategoryItemFlow(id: Long = 1): Flow<List<CategoryItemModel>> {
        return flow {
            try {
                homeRepository.getCategoryItemList(id).body()?.let { emit(it) }
            } catch (e: Exception) {
                _progressLiveData.postValue(Event(false))
            }
        }
    }

    private fun getCategoryFlow(): Flow<List<CategoryModel>> {

        return flow {

            try {
                homeRepository.getCategoryList().body()?.let { emit(it) }
            } catch (e: Exception) {
                _progressLiveData.postValue(Event(false))
            }

        }

    }





    fun fetchCombinedCategoryData(id: Long = 1) {
        viewModelScope.launch {
            listCategory
                .combine(getCategoryItemFlow(id)) { categoryList, categoryItemList ->
                    Pair(categoryList, categoryItemList)
                }
                .collect { combinedData ->
                    _combinedCategoryData.emit(combinedData)
                }

        }
    }
    fun getSessionListFlow(itemId: Long): Flow<List<SessionModel>> {

        return flow {

            try {
                homeRepository.getSessionList(itemId).body()?.let { emit(it) }
            } catch (e: Exception) {
                _progressLiveData.postValue(Event(false))

            }

        }

    }

    /*fun getCategoryList() {


        callSafeApi(requestMethod = {
            NetworkResponse(homeRepository.getCategoryList()).generateResponse()
        }, onStart = {
            _progressLiveData.postValue(Event(true))
        }, onSuccess = {
            data?.let { data ->
                _categoriesDataLiveData.postValue(data)
                _progressLiveData.postValue(Event(false))
            }
        }, onError = {

        })
    }

    fun getCategoryItemList(id: Long) {
        callSafeApi(requestMethod = {
            NetworkResponse(homeRepository.getCategoryItemList(id)).generateResponse()
        }, onStart = {
            _progressLiveData.postValue(Event(true))
        },

            onSuccess = {
                _progressLiveData.postValue(Event(false))
                data?.let { data ->
                    _categoryItemListLiveData.postValue(data)
                }
            },
            onCatch = {

            },
            onError = {

            })
    }

    fun getSessionList(id: Long) {
        callSafeApi(requestMethod = {
            NetworkResponse(homeRepository.getSessionList(id)).generateResponse()
        }, onStart = {
            _progressLiveData.postValue(Event(true))
        }, onSuccess = {
            data?.let { data ->
                _progressLiveData.postValue(Event(false))
                _sessionListLiveData.postValue(Event(data))
            }
        }, onError = {

        })
    }*/



}