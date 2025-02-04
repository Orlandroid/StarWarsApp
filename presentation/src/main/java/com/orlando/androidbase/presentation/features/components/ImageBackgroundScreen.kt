package com.orlando.androidbase.presentation.features.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ImageBackgroundScreen(
    modifier: Modifier = Modifier,
    imageBackgroundScreenConfig: ImageBackgroundScreenConfig,
    content: @Composable () -> Unit,
) {
    Box(modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(imageBackgroundScreenConfig.image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        content()
    }
}

data class ImageBackgroundScreenConfig(
    @DrawableRes
    val image: Int
)