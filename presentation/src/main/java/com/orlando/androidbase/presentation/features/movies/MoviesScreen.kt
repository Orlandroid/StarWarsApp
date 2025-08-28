package com.orlando.androidbase.presentation.features.movies

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Movie
import com.orlando.androidbase.presentation.extensions.LoadState
import com.orlando.androidbase.presentation.extensions.debugPlaceholder
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getFilmsImages


@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    clickOnItem: (Movie) -> Unit
) {
    val movies = viewModel.getFilmsPagingSource.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            count = movies.itemCount,
            key = movies.itemKey { it.title }
        ) { index ->
            movies[index]?.let { movie ->
                ItemMovie(
                    modifier = Modifier.fillMaxWidth(),
                    movie = movie,
                    onClick = clickOnItem
                )
            }
        }
        item {
            movies.LoadState(Modifier.fillParentMaxSize())
        }
    }
}


@Composable
fun ItemMovie(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: ((Movie)) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = Color.Black),
        onClick = { onClick(movie) }
    ) {
        val image = getImageFromJson(movie.title, getFilmsImages())
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),

            ) {
            AsyncImage(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(100.dp),
                model = image.ifEmpty {
                    R.drawable.films
                },
                placeholder = debugPlaceholder(R.drawable.films),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Spacer(Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = movie.title,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(), verticalArrangement = Arrangement.SpaceAround
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(R.string.producer), fontWeight = FontWeight.Bold)
                        Spacer(Modifier.width(16.dp))
                        Text(text = movie.producer)
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(R.string.premiere), fontWeight = FontWeight.Bold)
                        Spacer(Modifier.width(16.dp))
                        Text(text = movie.release)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemMoviePreview(modifier: Modifier = Modifier) {
    ItemMovie(
        movie = Movie(
            title = "Android",
            producer = "Episode",
            release = "release"
        ),
        onClick = {}
    )
}