package com.example.androidbase.presentation.ui.people

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.presentation.base.BaseViewModel
import com.example.androidbase.presentation.helpers.NetworkHelper
import com.example.data.di.CoroutineDispatchers
import com.example.data.pagination.CharactersPagingSource
import com.example.data.remote.ApiService
import com.example.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val apiService: ApiService,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    var characterName: String? = null

    init {
        characterName = null
    }

    private lateinit var charactersPagingSource: CharactersPagingSource

    val getCharactersPagingSource: Flow<PagingData<ResultPeople>> =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                charactersPagingSource =
                    CharactersPagingSource(service = apiService, name = characterName)
                charactersPagingSource
            }
        ).flow.cachedIn(viewModelScope)

    fun refreshCharactersPagingSource() = charactersPagingSource.invalidate()


}