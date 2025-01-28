package com.orlando.androidbase.presentation.features.people

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.People
import com.orlando.androidbase.entities.remote.toPeople
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getPeopleImages

@Composable
fun CharacterScreen(
    viewModel: PeopleViewModel = hiltViewModel()
) {
    val characters = viewModel.getCharactersPagingSource.collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(
            count = characters.itemCount,
            key = characters.itemKey { it.name }
        ) { index ->
            characters[index]?.let { character ->
                ItemCharacter(
                    modifier = Modifier.fillMaxWidth(),
                    character = character.toPeople()
                )
            }
        }
        when {
            characters.loadState.refresh is LoadState.Loading -> {
                item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
            }

            characters.loadState.refresh is LoadState.Error -> {
                item { androidx.compose.material.Text("Error") }
            }

            characters.loadState.append is LoadState.Loading -> {
                item {
                    Spacer(Modifier.height(16.dp))
                    LoadingNextPageItem(modifier = Modifier)
                }
            }

            characters.loadState.append is LoadState.Error -> {
                item {
                    androidx.compose.material.Text("Error")
                }
            }
        }
    }
}

@Composable
fun PageLoader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.strFetchingDataFromServer),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        CircularProgressIndicator(Modifier.padding(top = 10.dp))
    }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ItemCharacter(
    modifier: Modifier = Modifier, character: People
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = Color.Black)
    ) {
        val image = getImageFromJson(character.name, getPeopleImages())
        Row(Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = image.ifEmpty {
                    R.drawable.startwars
                },
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
fun ItemCharacterPreview(modifier: Modifier = Modifier) {
    ItemCharacter(character = People(name = "Luke Skywalker", gender = "Male"))
}