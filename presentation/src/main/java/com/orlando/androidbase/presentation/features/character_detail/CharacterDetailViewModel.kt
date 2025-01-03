package com.orlando.androidbase.presentation.features.character_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.orlando.androidbase.presentation.base.BaseViewModel
import com.orlando.androidbase.presentation.helpers.NetworkHelper
import com.orlando.data.Repository
import com.orlando.data.di.CoroutineDispatchers
import com.orlando.androidbase.entities.remote.ResultPeople
import com.orlando.androidbase.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private val _peopleDetailResponse = MutableLiveData<Result<ResultPeople>>()
    val peopleDetailResponse: LiveData<Result<ResultPeople>>
        get() = _peopleDetailResponse


    fun getPeopleDetail(idPeople: Int) {
        viewModelScope.launch {
            safeApiCall(_peopleDetailResponse, coroutineDispatchers) {
                val response = repository.getPeopleDetail(idPeople)
                withContext(Dispatchers.Main) {
                    _peopleDetailResponse.value = Result.Success(response)
                }
            }
        }
    }

}