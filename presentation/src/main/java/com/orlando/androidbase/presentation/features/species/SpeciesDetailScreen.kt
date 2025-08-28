package com.orlando.androidbase.presentation.features.species

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Specie
import com.orlando.androidbase.presentation.features.components.BaseDetailScreen
import com.orlando.androidbase.presentation.features.components.BaseDetailScreenConfig
import com.orlando.androidbase.presentation.features.components.RowItem
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getSpeciesImages

@Composable
fun SpeciesDetailScreenScreen(specie: Specie) {
    BaseDetailScreen(
        baseDetailScreenConfig = BaseDetailScreenConfig(
            image = getImageFromJson(
                specie.name,
                getSpeciesImages()
            ),
            error = R.drawable.species,
            placeHolder = R.drawable.species
        )
    ) {
        RowItem(
            key = stringResource(R.string.name),
            value = specie.name
        )
        RowItem(
            key = stringResource(R.string.classification),
            value = specie.classification
        )
        RowItem(
            key = stringResource(R.string.skin_colors),
            value = specie.skinColor
        )
        RowItem(
            key = stringResource(R.string.average_lifespan),
            value = specie.averageLifespan
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SpeciesDetailScreenScreenPreview() {
    SpeciesDetailScreenScreen(
        specie = Specie(
            name = stringResource(R.string.neimodian),
            classification = stringResource(R.string.unknown),
            skinColor = stringResource(R.string.grey_green),
            averageLifespan = stringResource(R.string.unknown)
        )
    )
}
