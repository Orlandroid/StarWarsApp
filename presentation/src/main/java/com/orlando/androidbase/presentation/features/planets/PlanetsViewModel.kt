package com.orlando.androidbase.presentation.features.planets

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orlando.androidbase.entities.remote.ResultPlanet
import com.orlando.androidbase.presentation.base.BaseViewModel
import com.orlando.androidbase.presentation.helpers.NetworkHelper
import com.orlando.data.di.CoroutineDispatchers
import com.orlando.data.pagination.PlanetsPagingSource
import com.orlando.data.remote.ApiService
import com.orlando.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
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

}