package com.orlando.androidbase.presentation.features.vehicles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orlando.androidbase.entities.remote.Vehicle
import com.orlando.androidbase.presentation.helpers.NetworkHelper
import com.orlando.data.di.CoroutineDispatchers
import com.orlando.data.pagination.VehiclesPagingSource
import com.orlando.data.remote.ApiService
import com.orlando.data.utils.getPagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class VehiclesViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {


    private lateinit var vehiclesPagingSource: VehiclesPagingSource

    val getVehiclesPagingSource: Flow<PagingData<Vehicle>> =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                vehiclesPagingSource = VehiclesPagingSource(service = apiService)
                vehiclesPagingSource
            }
        ).flow.cachedIn(viewModelScope)
}