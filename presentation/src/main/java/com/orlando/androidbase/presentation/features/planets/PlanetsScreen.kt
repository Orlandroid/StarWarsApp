package com.orlando.androidbase.presentation.features.planets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Planet
import com.orlando.androidbase.presentation.extensions.LoadState
import com.orlando.androidbase.presentation.extensions.debugPlaceholder
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getPlanetsImages
import kotlinx.coroutines.flow.flowOf


@Composable
fun PlanetsScreen(
    planets: LazyPagingItems<Planet>,
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
                    planet = planet,
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
private fun ItemPlanet(
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
        val image = getImageFromJson(planet.name, getPlanetsImages())
        Row(
            Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(100.dp),
                model = image.ifEmpty {
                    R.drawable.planets
                },
                placeholder = debugPlaceholder(R.drawable.planets),
                contentDescription = null
            )
            PlanetProperties(planet = planet)
        }
    }
}

@Composable
private fun PlanetProperties(planet: Planet) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = planet.name,
            fontWeight = FontWeight.Bold
        )
        Row(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.population),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = planet.population
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.climate),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = planet.climate
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.terrain),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center,
                text = planet.terrain
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ItemPreview() {
    ItemPlanet(
        planet = Planet(
            name = stringResource(R.string.yoda),
            population = stringResource(R.string._200000),
            climate = stringResource(R.string.arid),
            terrain = stringResource(R.string.desert)
        ),
        onClick = {}
    )
}

@Composable
@Preview(showBackground = true)
private fun PlanetsScreenPreview() {
    val mockPlanet = Planet(
        name = stringResource(R.string.geonosis),
        population = stringResource(R.string._100000000000),
        climate = stringResource(R.string.temperate_arid),
        terrain = stringResource(R.string.geonosis)
    )
    val items = flowOf(
        PagingData.from(
            listOf(
                mockPlanet,
                mockPlanet
            )
        )
    ).collectAsLazyPagingItems()
    PlanetsScreen(planets = items, clickOnItem = {})
}

