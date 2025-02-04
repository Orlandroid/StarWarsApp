package com.orlando.androidbase.presentation.features.planets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Planet
import com.orlando.androidbase.presentation.features.components.BaseDetailScreen
import com.orlando.androidbase.presentation.features.components.BaseDetailScreenConfig
import com.orlando.androidbase.presentation.features.components.RowItem
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getPlanetsImages


@Composable
fun PlanetDetailScreen(planet: Planet) {
    BaseDetailScreen(
        baseDetailScreenConfig = BaseDetailScreenConfig(
            image = getImageFromJson(
                planet.name,
                getPlanetsImages()
            ),
            placeHolder = R.drawable.planets,
            error = R.drawable.planets
        )
    ) {
        RowItem(
            key = stringResource(R.string.name),
            value = planet.name
        )
        RowItem(
            key = stringResource(R.string.climate),
            value = planet.climate
        )
        RowItem(
            key = stringResource(R.string.population),
            value = planet.population
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PlanetDetailScreenPreview(modifier: Modifier = Modifier) {
    val mockPlanet = Planet(
        name = stringResource(R.string.geonosis),
        population = stringResource(R.string._100000000000),
        climate = stringResource(R.string.temperate_arid),
        terrain = stringResource(R.string.geonosis)
    )
    PlanetDetailScreen(
        planet = mockPlanet
    )
}