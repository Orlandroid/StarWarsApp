package com.orlando.androidbase.presentation.features.character_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orlando.androidbase.R
import com.orlando.androidbase.entities.remote.People
import com.orlando.androidbase.presentation.features.components.BaseDetailScreen
import com.orlando.androidbase.presentation.features.components.BaseDetailScreenConfig
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getPeopleImages


@Composable
fun CharacterDetailScreen(people: People) {
    BaseDetailScreen(
        baseDetailScreenConfig = BaseDetailScreenConfig(
            image = getImageFromJson(
                people.name,
                getPeopleImages()
            ),
            placeHolder = R.drawable.character,
            error = R.drawable.character
        )
    ) {
        ItemKeyValue(
            key = stringResource(R.string.name),
            value = people.name
        )
        ItemKeyValue(
            key = stringResource(R.string.height),
            value = people.height
        )
        ItemKeyValue(
            key = stringResource(R.string.gender),
            value = people.gender
        )
        ItemKeyValue(
            key = stringResource(R.string.hair_color),
            value = people.hairColor
        )
        ItemKeyValue(
            key = stringResource(R.string.mass),
            value = people.mass
        )
        ItemKeyValue(
            key = stringResource(R.string.skin_color),
            value = people.skinColor
        )
    }
}

@Composable
fun ItemKeyValue(
    modifier: Modifier = Modifier,
    key: String,
    value: String
) {
    Spacer(Modifier.height(16.dp))
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.width(16.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = key,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.width(32.dp))
        Text(modifier = Modifier.weight(1f), text = value, fontSize = 18.sp)
    }
}

@Composable
@Preview(showBackground = true)
fun CharacterDetailScreenPreview() {
    CharacterDetailScreen(
        People(
            name = "Luke Skywalker",
            gender = "Male",
            height = "188",
            hairColor = "blond",
            mass = "84",
            skinColor = "fair"
        )
    )
}