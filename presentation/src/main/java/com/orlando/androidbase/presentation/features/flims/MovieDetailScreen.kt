package com.orlando.androidbase.presentation.features.flims

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.Movie
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getFilmsImages


@Composable
fun MovieDetailScreen(movie: Movie) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.background)),
        shape = RoundedCornerShape(0.dp)
    ) {
        val image = getImageFromJson(movie.title, getFilmsImages())
        Spacer(Modifier.height(16.dp))
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),
            model = image,
            error = painterResource(R.drawable.films),
            contentDescription = null
        )
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
private fun RowItem(
    key: String,
    value: String
) {
    val style = androidx.compose.ui.text.TextStyle(
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            style = style,
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
            text = key,
            textAlign = TextAlign.Center,
        )
        Text(
            style = style.copy(fontWeight = null),
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
            text = value,
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
@Preview(showBackground = true)
fun MovieDetailScreenPreview(modifier: Modifier = Modifier) {
    MovieDetailScreen(
        movie = Movie(
            title = stringResource(R.string.a_new_hope),
            producer = stringResource(R.string.gary_kurtz_rick_mccallum),
            release = "1977-05-25"
        )
    )
}
