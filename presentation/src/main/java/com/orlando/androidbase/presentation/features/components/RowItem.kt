package com.orlando.androidbase.presentation.features.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowItem(
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
                .padding(top = 16.dp, end = 8.dp),
            text = value,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun RowItemPreview() {
    RowItem(key = "Android", "Developer")
}