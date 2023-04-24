package com.example.androidbase.presentation.ui.vehicles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidbase.entities.remote.VehiclesResponse
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
class VehiclesViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private val _vehiclesResponse = MutableLiveData<Result<VehiclesResponse>>()
    val vehiclesResponse: LiveData<Result<VehiclesResponse>>
        get() = _vehiclesResponse


    fun getVehicles(page: String) {
        viewModelScope.launch {
            safeApiCall(_vehiclesResponse, coroutineDispatchers) {
                val response = repository.getVehicles(page)
                withContext(Dispatchers.Main) {
                    _vehiclesResponse.value = Result.Success(response)
                }
            }
        }
    }

}