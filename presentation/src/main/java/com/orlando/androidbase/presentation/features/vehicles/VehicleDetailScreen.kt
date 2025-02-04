package com.orlando.androidbase.presentation.features.vehicles

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Vehicle
import com.orlando.androidbase.presentation.features.components.BaseDetailScreen
import com.orlando.androidbase.presentation.features.components.BaseDetailScreenConfig
import com.orlando.androidbase.presentation.features.components.RowItem
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getStartShipsImages

@Composable
fun VehicleDetailScreen(vehicle: Vehicle) {
    BaseDetailScreen(
        baseDetailScreenConfig = BaseDetailScreenConfig(
            image = getImageFromJson(
                vehicle.name,
                getStartShipsImages()
            ),
            error = R.drawable.vehicles,
            placeHolder = R.drawable.vehicles
        )
    ) {
        RowItem(key = stringResource(R.string.name), value = vehicle.name)
        RowItem(key = stringResource(R.string.manufacturer), value = vehicle.manufacturer)
        RowItem(
            key = stringResource(R.string.max_atmosphering_speed),
            value = vehicle.maxAtmospheringSpeed
        )
        RowItem(key = stringResource(R.string.consumables), value = vehicle.consumables)
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleDetailScreenPreview() {
    VehicleDetailScreen(
        vehicle = Vehicle(
            name = stringResource(R.string.sand_crawler),
            manufacturer = stringResource(R.string.corellia_mining_corporation),
            maxAtmospheringSpeed = stringResource(R.string._30),
            consumables = stringResource(R.string._2_months)
        )
    )
}
