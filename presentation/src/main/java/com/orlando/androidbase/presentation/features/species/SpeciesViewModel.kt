package com.orlando.androidbase.presentation.features.species

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.orlando.data.pagination.SpeciesPagingSource
import com.orlando.data.remote.ApiService
import com.orlando.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpeciesViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {


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