package com.example.androidbase.presentation.ui.planets

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidbase.entities.remote.ResultPlanet
import com.example.androidbase.presentation.base.BaseViewModel
import com.example.androidbase.presentation.helpers.NetworkHelper
import com.example.data.Repository
import com.example.data.di.CoroutineDispatchers
import com.example.data.pagination.PlanetsPagingSource
import com.example.data.remote.ApiService
import com.example.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val repository: Repository,
    private val apiService: ApiService,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private lateinit var planetsPagingSource: PlanetsPagingSource

    val getPlanetsPagingSource: Flow<PagingData<ResultPlanet>> =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                planetsPagingSource = PlanetsPagingSource(service = apiService)
                planetsPagingSource
            }
        ).flow.cachedIn(viewModelScope)

    fun refreshPlanetsPagingSource() = planetsPagingSource.invalidate()

}