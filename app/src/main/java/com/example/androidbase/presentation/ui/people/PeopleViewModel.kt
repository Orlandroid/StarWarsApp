package com.example.androidbase.presentation.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidbase.presentation.base.BaseViewModel
import com.example.androidbase.presentation.helpers.NetworkHelper
import com.example.data.Repository
import com.example.data.di.CoroutineDispatchers
import com.example.domain.entities.remote.PeopleResponseItem
import com.example.domain.entities.remote.ResultResponse
import com.example.domain.state.Result
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


    private val _peopleResponse = MutableLiveData<Result<ResultResponse>>()
    val peopleResponse: LiveData<Result<ResultResponse>>
        get() = _peopleResponse

    private val _allPeopleResponse = MutableLiveData<Result<List<PeopleResponseItem>>>()
    val allPeopleResponse: LiveData<Result<List<PeopleResponseItem>>>
        get() = _allPeopleResponse

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


}