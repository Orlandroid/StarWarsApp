package com.orlando.androidbase.presentation.features.characters

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.People
import com.orlando.androidbase.presentation.extensions.LoadState
import com.orlando.androidbase.presentation.extensions.debugPlaceholder
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getPeopleImages

@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = hiltViewModel(),
    clickOnItem: (people: People) -> Unit = {}
) {
    val characters = viewModel.getCharactersPagingSource.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            count = characters.itemCount,
            key = characters.itemKey { it.name }
        ) { index ->
            characters[index]?.let { character ->
                ItemCharacter(
                    modifier = Modifier.fillMaxWidth(),
                    character = character,
                    clickOnItem = clickOnItem
                )
            }
        }
        item {
            characters.LoadState(Modifier.fillParentMaxSize())
        }
    }
}

@Composable
private fun ItemCharacter(
    modifier: Modifier = Modifier,
    character: People,
    clickOnItem: (people: People) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = Color.Black),
        onClick = {
            clickOnItem(character)
        }
    ) {
        val image = getImageFromJson(character.name, getPeopleImages())
        Row(Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = image.ifEmpty {
                    R.drawable.startwars
                },
                placeholder = debugPlaceholder(R.drawable.startwars),
                contentDescription = null
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = character.name,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = character.gender
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ItemCharacterPreview() {
    ItemCharacter(
        character = People(
            name = "Luke Skywalker",
            gender = "Male",
            height = "188",
            hairColor = "blond",
            mass = "84",
            skinColor = "fair"
        ),
        clickOnItem = {}
    )
}