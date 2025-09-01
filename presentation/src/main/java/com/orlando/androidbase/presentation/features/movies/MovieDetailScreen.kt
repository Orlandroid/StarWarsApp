package com.orlando.androidbase.presentation.features.movies

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Movie
import com.orlando.androidbase.presentation.features.components.BaseDetailScreen
import com.orlando.androidbase.presentation.features.components.BaseDetailScreenConfig
import com.orlando.androidbase.presentation.features.components.RowItem
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getFilmsImages


@Composable
fun MovieDetailScreen(movie: Movie) {
    BaseDetailScreen(
        baseDetailScreenConfig = BaseDetailScreenConfig(
            image = getImageFromJson(movie.title, getFilmsImages()),
            placeHolder = R.drawable.films,
            error = R.drawable.films
        )
    ) {
        RowItem(
            key = stringResource(R.string.title),
            value = movie.title
        )
        RowItem(
            key = stringResource(R.string.producer),
            value = movie.producer
        )
        RowItem(
            key = stringResource(R.string.release_date),
            value = movie.release
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun MovieDetailScreenPreview() {
    MovieDetailScreen(
        movie = Movie(
            title = stringResource(R.string.a_new_hope),
            producer = stringResource(R.string.gary_kurtz_rick_mccallum),
            release = "1977-05-25"
        )
    )
}
