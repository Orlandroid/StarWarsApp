package com.example.androidbase.presentation.ui.starships

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidbase.entities.remote.FlimsResponse
import com.example.androidbase.entities.remote.ResultResponse
import com.example.androidbase.entities.remote.StarshipsResponse
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
class StarshipViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private val _starshipsResponse = MutableLiveData<Result<StarshipsResponse>>()
    val starshipsResponse: LiveData<Result<StarshipsResponse>>
        get() = _starshipsResponse


    fun getStarships(page: String) {
        viewModelScope.launch {
            safeApiCall(_starshipsResponse, coroutineDispatchers) {
                val response = repository.getStarships(page)
                withContext(Dispatchers.Main) {
                    _starshipsResponse.value = Result.Success(response)
                }
            }
        }
    }

}