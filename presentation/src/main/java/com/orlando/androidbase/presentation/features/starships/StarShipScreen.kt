package com.orlando.androidbase.presentation.features.starships

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
import com.orlando.androidbase.entities.remote.ResultStarship
import com.orlando.androidbase.entities.remote.Starship
import com.orlando.androidbase.entities.remote.toStarship
import com.orlando.androidbase.presentation.extensions.LoadState


@Composable
fun StarShipScreen(
    starShips: LazyPagingItems<ResultStarship>,
    clickOnItem: (Starship) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            count = starShips.itemCount,
            key = starShips.itemKey { it.name }
        ) { index ->
            starShips[index]?.let { starShip ->
                StarShipItem(
                    modifier = Modifier.fillMaxWidth(),
                    starship = starShip.toStarship(),
                    onClick = clickOnItem
                )
            }
        }
        item {
            starShips.LoadState(Modifier.fillParentMaxSize())
        }
    }
}

@Composable
fun StarShipItem(
    modifier: Modifier = Modifier,
    starship: Starship,
    onClick: (Starship) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = Color.Black),
        onClick = { onClick(starship) }
    ) {

    }
}

