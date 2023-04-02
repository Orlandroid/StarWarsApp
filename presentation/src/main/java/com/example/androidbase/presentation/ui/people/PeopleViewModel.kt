package com.example.androidbase.presentation.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidbase.presentation.base.BaseViewModel
import com.example.androidbase.presentation.helpers.NetworkHelper
import com.example.data.Repository
import com.example.data.di.CoroutineDispatchers
import com.example.androidbase.entities.remote.PeopleResponseItem
import com.example.androidbase.entities.remote.ResultResponse
import com.example.androidbase.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private val _allPeopleResponse = MutableLiveData<Result<List<PeopleResponseItem>>>()
    val allPeopleResponse: LiveData<Result<List<PeopleResponseItem>>>
        get() = _allPeopleResponse

    private val _peopleDetailResponse = MutableLiveData<Result<PeopleResponseItem?>>()
    val peopleDetailResponse: LiveData<Result<PeopleResponseItem?>>
        get() = _peopleDetailResponse

    private val _peopleResponse = MutableLiveData<Result<ResultResponse>>()
    val peopleResponse: LiveData<Result<ResultResponse>>
        get() = _peopleResponse

    fun getAllPeople() {
        viewModelScope.launch {
            safeApiCall(_allPeopleResponse, coroutineDispatchers) {
                val response = repository.getAllPeople()
                withContext(Dispatchers.Main) {
                    _allPeopleResponse.value = Result.Success(response)
                }
            }
        }
    }

    fun getPeopleDetail(peopleId: Int) {
        viewModelScope.launch {
            safeApiCall(_peopleDetailResponse, coroutineDispatchers) {
                val response = getPeopleById(repository.getAllPeople(), peopleId)
                withContext(Dispatchers.Main) {
                    _peopleDetailResponse.value = Result.Success(response)
                }
            }
        }
    }

    fun getPeople(page: String) {
        viewModelScope.launch {
            safeApiCall(_peopleResponse, coroutineDispatchers) {
                val response = repository.getPeople(page)
                withContext(Dispatchers.Main) {
                    _peopleResponse.value = Result.Success(response)
                }
            }
        }
    }

    private fun getPeopleById(
        peopleList: List<PeopleResponseItem>,
        peopleId: Int
    ): PeopleResponseItem? {
        peopleList.forEach {
            if (it.id == peopleId) {
                return it
            }
        }
        return null
    }


}