package com.example.androidbase.presentation.ui.starships

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.entities.remote.ResultStarship
import com.example.androidbase.presentation.base.BaseViewModel
import com.example.androidbase.presentation.helpers.NetworkHelper
import com.example.data.Repository
import com.example.data.di.CoroutineDispatchers
import com.example.data.pagination.CharactersPagingSource
import com.example.data.pagination.StarshipsPagingSource
import com.example.data.remote.ApiService
import com.example.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StarshipViewModel @Inject constructor(
    private val repository: Repository,
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