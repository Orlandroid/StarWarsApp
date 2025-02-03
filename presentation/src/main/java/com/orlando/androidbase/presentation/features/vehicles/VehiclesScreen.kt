package com.orlando.androidbase.presentation.features.vehicles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Vehicle
import com.orlando.androidbase.presentation.extensions.LoadState
import com.orlando.androidbase.presentation.extensions.debugPlaceholder
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getVehiclesImages

@Composable
fun VehiclesScreen(
    vehicles: LazyPagingItems<Vehicle>,
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
                VehicleItem(
                    modifier = Modifier.fillMaxWidth(),
                    vehicle = starShip,
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
fun VehicleItem(
    modifier: Modifier = Modifier,
    vehicle: Vehicle,
    onClick: (Vehicle) -> Unit
) {
    val image = getImageFromJson(vehicle.name, getVehiclesImages())
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = Color.Black),
        onClick = { onClick(vehicle) }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp),
                model = image.ifEmpty {
                    R.drawable.vehicles
                },
                placeholder = debugPlaceholder(R.drawable.vehicles),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    text = vehicle.name,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.manufacturer)
                    )
                    Spacer(Modifier.width(16.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = vehicle.manufacturer
                    )
                }
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.model)
                    )
                    Spacer(Modifier.width(16.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = vehicle.model
                    )
                }
            }
        }
    }
}