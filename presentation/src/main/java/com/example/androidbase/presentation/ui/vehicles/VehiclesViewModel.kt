package com.example.androidbase.presentation.ui.vehicles

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidbase.entities.remote.ResultVehicle
import com.example.androidbase.presentation.base.BaseViewModel
import com.example.androidbase.presentation.helpers.NetworkHelper
import com.example.data.Repository
import com.example.data.di.CoroutineDispatchers
import com.example.data.pagination.VehiclesPagingSource
import com.example.data.remote.ApiService
import com.example.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class VehiclesViewModel @Inject constructor(
    private val repository: Repository,
    private val apiService: ApiService,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private lateinit var vehiclesPagingSource: VehiclesPagingSource

    val getVehiclesPagingSource: Flow<PagingData<ResultVehicle>> =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                vehiclesPagingSource = VehiclesPagingSource(service = apiService)
                vehiclesPagingSource
            }
        ).flow.cachedIn(viewModelScope)

    fun refreshVehiclesPagingSource() = vehiclesPagingSource.invalidate()

}