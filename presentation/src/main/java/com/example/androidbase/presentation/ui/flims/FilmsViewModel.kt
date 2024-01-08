package com.example.androidbase.presentation.ui.flims

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidbase.entities.remote.Film
import com.example.androidbase.presentation.base.BaseViewModel
import com.example.androidbase.presentation.helpers.NetworkHelper
import com.example.data.Repository
import com.example.data.di.CoroutineDispatchers
import com.example.data.pagination.FilmsPagingSource
import com.example.data.remote.ApiService
import com.example.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val repository: Repository,
    private val apiService: ApiService,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private lateinit var filmsPagingSource: FilmsPagingSource

    val getFilmsPagingSource: Flow<PagingData<Film>> =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                filmsPagingSource = FilmsPagingSource(service = apiService)
                filmsPagingSource
            }
        ).flow.cachedIn(viewModelScope)

    fun refreshFilmsPagingSource() = filmsPagingSource.invalidate()

}