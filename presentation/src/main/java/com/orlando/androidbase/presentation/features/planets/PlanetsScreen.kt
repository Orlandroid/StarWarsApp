package com.orlando.androidbase.presentation.features.planets

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
import com.orlando.androidbase.entities.remote.Planet
import com.orlando.androidbase.entities.remote.ResultPlanet
import com.orlando.androidbase.entities.remote.toPlanet
import com.orlando.androidbase.presentation.extensions.LoadState


@Composable
fun PlanetsScreen(
    planets: LazyPagingItems<ResultPlanet>,
    clickOnItem: (Planet) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            count = planets.itemCount,
            key = planets.itemKey { it.name }
        ) { index ->
            planets[index]?.let { planet ->
                ItemPlanet(
                    modifier = Modifier.fillMaxWidth(),
                    planet = planet.toPlanet(),
                    onClick = clickOnItem
                )
            }
        }
        item {
            planets.LoadState(Modifier.fillParentMaxSize())
        }
    }
}

@Composable
fun ItemPlanet(
    modifier: Modifier = Modifier,
    planet: Planet,
    onClick: (Planet) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = Color.Black),
        onClick = { onClick(planet) }
    ) {

    }
}

