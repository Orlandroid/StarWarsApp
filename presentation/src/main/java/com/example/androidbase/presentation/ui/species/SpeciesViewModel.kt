package com.example.androidbase.presentation.ui.species

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.example.androidbase.presentation.base.BaseViewModel
import com.example.androidbase.presentation.helpers.NetworkHelper
import com.example.data.di.CoroutineDispatchers
import com.example.data.pagination.SpeciesPagingSource
import com.example.data.remote.ApiService
import com.example.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpeciesViewModel @Inject constructor(
    private val apiService: ApiService,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private lateinit var speciesPagingSource: SpeciesPagingSource

    val getSpeciesPagingSource =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                speciesPagingSource = SpeciesPagingSource(service = apiService)
                speciesPagingSource
            }
        ).flow.cachedIn(viewModelScope)

    fun refreshCharactersPagingSource() = speciesPagingSource.invalidate()

}