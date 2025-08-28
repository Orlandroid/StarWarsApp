package com.orlando.androidbase.presentation.features.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orlando.androidbase.entities.remote.Planet
import com.orlando.data.pagination.PlanetsPagingSource
import com.orlando.data.remote.ApiService
import com.orlando.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {


    private lateinit var planetsPagingSource: PlanetsPagingSource

    val getPlanetsPagingSource: Flow<PagingData<Planet>> =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                planetsPagingSource = PlanetsPagingSource(service = apiService)
                planetsPagingSource
            }
        ).flow.cachedIn(viewModelScope)

}