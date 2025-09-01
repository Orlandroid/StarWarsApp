package com.orlando.androidbase.presentation.features.starships

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Starship
import com.orlando.androidbase.presentation.extensions.LoadState
import com.orlando.androidbase.presentation.extensions.debugPlaceholder
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getStartShipsImages


@Composable
fun StarShipScreen(
    starShips: LazyPagingItems<Starship>,
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
                    starship = starShip,
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
private fun StarShipItem(
    modifier: Modifier = Modifier,
    starship: Starship,
    onClick: (Starship) -> Unit
) {
    val image = getImageFromJson(starship.name, getStartShipsImages())
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = Color.Black),
        onClick = { onClick(starship) }
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
                    R.drawable.starships
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
                    text = starship.name,
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
                        text = starship.manufacturer
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
                        text = starship.model
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun StarShipItemPreview() {
    StarShipItem(
        starship = Starship(
            name = stringResource(R.string.name),
            manufacturer = stringResource(R.string.name),
            model = stringResource(R.string.name),
            maxAtmosphericSpeed = stringResource(R.string.detail)
        ),
        onClick = {})
}

