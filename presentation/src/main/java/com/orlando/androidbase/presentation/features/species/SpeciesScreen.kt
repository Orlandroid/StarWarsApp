package com.orlando.androidbase.presentation.features.species

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
import com.orlando.androidbase.entities.remote.Specie
import com.orlando.androidbase.presentation.extensions.LoadState
import com.orlando.androidbase.presentation.extensions.debugPlaceholder
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getSpeciesImages


@Composable
fun SpeciesScreen(
    species: LazyPagingItems<Specie>,
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
                    specie = specie,
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
        val image = getImageFromJson(specie.name, getSpeciesImages())
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
                    R.drawable.species
                },
                placeholder = debugPlaceholder(R.drawable.startwars),
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
                    text = stringResource(R.string.name),
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    textAlign = TextAlign.Center,
                    text = specie.name
                )

            }
        }
    }
}

