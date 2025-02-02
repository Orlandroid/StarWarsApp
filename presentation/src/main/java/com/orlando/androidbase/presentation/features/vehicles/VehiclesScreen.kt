package com.orlando.androidbase.presentation.features.vehicles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.orlando.androidbase.entities.remote.ResultVehicle
import com.orlando.androidbase.entities.remote.Vehicle
import com.orlando.androidbase.entities.remote.toVehicle
import com.orlando.androidbase.presentation.extensions.LoadState

@Composable
fun VehiclesScreen(
    vehicles: LazyPagingItems<ResultVehicle>,
    clickOnItem: (Vehicle) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            count = vehicles.itemCount,
            key = vehicles.itemKey { it.name }
        ) { index ->
            vehicles[index]?.let { starShip ->
                StarShipItem(
                    modifier = Modifier.fillMaxWidth(),
                    vehicle = starShip.toVehicle(),
                    onClick = clickOnItem
                )
            }
        }
        item {
            vehicles.LoadState(Modifier.fillParentMaxSize())
        }
    }
}

@Composable
fun StarShipItem(
    modifier: Modifier = Modifier,
    vehicle: Vehicle,
    onClick: (Vehicle) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = Color.Black),
        onClick = { onClick(vehicle) }
    ) {

    }
}