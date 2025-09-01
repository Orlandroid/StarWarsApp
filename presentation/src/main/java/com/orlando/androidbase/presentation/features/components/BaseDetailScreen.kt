package com.orlando.androidbase.presentation.features.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.orlando.androidbase.R
import com.orlando.androidbase.presentation.extensions.debugPlaceholder


@Composable
fun BaseDetailScreen(
    baseDetailScreenConfig: BaseDetailScreenConfig,
    propertiesContent: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.background)
        ),
        shape = RoundedCornerShape(0.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),
            model = baseDetailScreenConfig.image,
            placeholder = debugPlaceholder(baseDetailScreenConfig.placeHolder),
            error = painterResource(baseDetailScreenConfig.error),
            contentDescription = null
        )
        propertiesContent()
        Spacer(Modifier.height(16.dp))
    }
}

data class BaseDetailScreenConfig(
    val image: String,
    @DrawableRes
    val placeHolder: Int,
    @DrawableRes
    val error: Int,
)

@Composable
@Preview(showBackground = true)
private fun BaseDetailScreenPreview() {
    BaseDetailScreen(
        baseDetailScreenConfig = BaseDetailScreenConfig(
            image = "",
            placeHolder = R.drawable.character,
            error = R.drawable.character
        )
    ) {
        RowItem(
            key = stringResource(R.string.title),
            value = "planet.name"
        )
        RowItem(
            key = stringResource(R.string.producer),
            value = "planet.climate"
        )
        RowItem(
            key = stringResource(R.string.release_date),
            value = "planet.population"
        )
    }
}