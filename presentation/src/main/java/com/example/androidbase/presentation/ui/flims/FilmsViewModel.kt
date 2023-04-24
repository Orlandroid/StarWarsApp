package com.example.androidbase.presentation.ui.flims

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidbase.entities.remote.FlimsResponse
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
class FilmsViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private val _filmsResponse = MutableLiveData<Result<FlimsResponse>>()
    val filmsResponse: LiveData<Result<FlimsResponse>>
        get() = _filmsResponse


    fun getFilms(page: String) {
        viewModelScope.launch {
            safeApiCall(_filmsResponse, coroutineDispatchers) {
                val response = repository.getFilms(page)
                withContext(Dispatchers.Main) {
                    _filmsResponse.value = Result.Success(response)
                }
            }
        }
    }

}