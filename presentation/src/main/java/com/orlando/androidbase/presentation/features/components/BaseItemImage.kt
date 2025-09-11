package com.orlando.androidbase.presentation.features.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.orlando.androidbase.R
import kotlin.text.ifEmpty

@Composable
fun BaseItemImage(
    image: String,
    contentDescription: String,
    @DrawableRes onError: Int
) {
    SubcomposeAsyncImage(
        modifier = Modifier.size(100.dp),
        model = image.ifEmpty {
            R.drawable.character
        },
        loading = {
            CircularProgressIndicator(Modifier.padding(8.dp))
        },
        contentDescription = contentDescription,
        error = {
            Image(contentDescription = contentDescription, painter = painterResource(onError))
        }
    )
}