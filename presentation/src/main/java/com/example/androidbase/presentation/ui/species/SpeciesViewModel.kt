package com.example.androidbase.presentation.ui.species

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidbase.entities.remote.FlimsResponse
import com.example.androidbase.entities.remote.ResultResponse
import com.example.androidbase.entities.remote.SpeciesResponse
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
class SpeciesViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private val _speciesResponse = MutableLiveData<Result<SpeciesResponse>>()
    val speciesResponse: LiveData<Result<SpeciesResponse>>
        get() = _speciesResponse


    fun getSpecies(page: String) {
        viewModelScope.launch {
            safeApiCall(_speciesResponse, coroutineDispatchers) {
                val response = repository.getSpecies(page)
                withContext(Dispatchers.Main) {
                    _speciesResponse.value = Result.Success(response)
                }
            }
        }
    }

}