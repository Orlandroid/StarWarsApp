package com.orlando.androidbase.presentation.features.starships

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orlando.androidbase.entities.remote.ResultStarship
import com.orlando.androidbase.presentation.base.BaseViewModel
import com.orlando.androidbase.presentation.helpers.NetworkHelper
import com.orlando.data.di.CoroutineDispatchers
import com.orlando.data.pagination.StarshipsPagingSource
import com.orlando.data.remote.ApiService
import com.orlando.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StarshipViewModel @Inject constructor(
    private val apiService: ApiService,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private lateinit var starshipsPagingSource: StarshipsPagingSource

    val getStarshipsPagingSource: Flow<PagingData<ResultStarship>> =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                starshipsPagingSource = StarshipsPagingSource(service = apiService)
                starshipsPagingSource
            }
        ).flow.cachedIn(viewModelScope)

    fun refreshStarshipsPagingSource() = starshipsPagingSource.invalidate()

}