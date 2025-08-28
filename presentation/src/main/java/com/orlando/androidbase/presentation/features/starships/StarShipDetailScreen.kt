package com.orlando.androidbase.presentation.features.starships


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Starship
import com.orlando.androidbase.presentation.features.components.BaseDetailScreen
import com.orlando.androidbase.presentation.features.components.BaseDetailScreenConfig
import com.orlando.androidbase.presentation.features.components.RowItem
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getStartShipsImages

@Composable
fun StarShipDetailScreenScreen(starship: Starship) {
    BaseDetailScreen(
        baseDetailScreenConfig = BaseDetailScreenConfig(
            image = getImageFromJson(
                starship.name,
                getStartShipsImages()
            ),
            error = R.drawable.starships,
            placeHolder = R.drawable.starships
        )
    ) {
        RowItem(key = stringResource(R.string.name), value = starship.name)
        RowItem(key = stringResource(R.string.model), value = starship.model)
        RowItem(key = stringResource(R.string.manufacturer), value = starship.manufacturer)
        RowItem(
            key = stringResource(R.string.max_atmosphering_speed),
            value = starship.maxAtmosphericSpeed
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StarShipDetailScreenScreenPreview() {
    StarShipDetailScreenScreen(
        starship = Starship(
            name = stringResource(R.string.death_star),
            manufacturer = stringResource(R.string.ds_1_orbital_battle_station),
            model = stringResource(R.string.imperial_department_of_military_research_sienar_fleet_systems),
            maxAtmosphericSpeed = stringResource(R.string._950)
        )
    )
}
