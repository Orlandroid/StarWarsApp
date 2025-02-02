package com.orlando.androidbase.presentation.features.species

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
import com.orlando.androidbase.entities.remote.ResultSpecie
import com.orlando.androidbase.entities.remote.Specie
import com.orlando.androidbase.entities.remote.toSpecie
import com.orlando.androidbase.presentation.extensions.LoadState


@Composable
fun SpeciesScreen(
    species: LazyPagingItems<ResultSpecie>,
    clickOnItem: (Specie) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            count = species.itemCount,
            key = species.itemKey { it.name }
        ) { index ->
            species[index]?.let { specie ->
                SpecieItem(
                    modifier = Modifier.fillMaxWidth(),
                    specie = specie.toSpecie(),
                    onClick = clickOnItem
                )
            }
        }
        item {
            species.LoadState(Modifier.fillParentMaxSize())
        }
    }
}

@Composable
fun SpecieItem(
    modifier: Modifier = Modifier,
    specie: Specie,
    onClick: (Specie) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = Color.Black),
        onClick = { onClick(specie) }
    ) {

    }
}

