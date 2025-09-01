package com.orlando.androidbase.presentation.features.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orlando.androidbase.entities.remote.Movie
import com.orlando.androidbase.presentation.helpers.NetworkHelper
import com.orlando.data.di.CoroutineDispatchers
import com.orlando.data.pagination.FilmsPagingSource
import com.orlando.data.remote.ApiService
import com.orlando.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {


    private lateinit var filmsPagingSource: FilmsPagingSource

    val getFilmsPagingSource: Flow<PagingData<Movie>> =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                filmsPagingSource = FilmsPagingSource(service = apiService)
                filmsPagingSource
            }
        ).flow.cachedIn(viewModelScope)


}