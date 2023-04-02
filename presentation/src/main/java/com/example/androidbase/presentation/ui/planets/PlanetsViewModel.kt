package com.example.androidbase.presentation.ui.planets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidbase.entities.remote.PlanetsResponse
import com.example.androidbase.presentation.base.BaseViewModel
import com.example.androidbase.presentation.helpers.NetworkHelper
import com.example.androidbase.state.Result
import com.example.data.Repository
import com.example.data.di.CoroutineDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private val _planetsResponse = MutableLiveData<Result<PlanetsResponse>>()
    val planetsResponse: LiveData<Result<PlanetsResponse>>
        get() = _planetsResponse


    fun getPlanets(page: String) {
        viewModelScope.launch {
            safeApiCall(_planetsResponse, coroutineDispatchers) {
                val response = repository.getPlanets(page)
                withContext(Dispatchers.Main) {
                    _planetsResponse.value = Result.Success(response)
                }
            }
        }
    }

}