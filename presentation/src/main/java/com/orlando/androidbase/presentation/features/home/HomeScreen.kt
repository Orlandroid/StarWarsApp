package com.orlando.androidbase.presentation.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orlando.androidbase.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    menus: List<HomeAdapter.ItemMenu>
) {
    Box(modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(R.drawable.bg_stars),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(Modifier.fillMaxWidth()) {
            Image(
                contentDescription = "",
                painter = painterResource(R.drawable.startwars),
                modifier = Modifier
                    .fillMaxWidth()

            )
            Spacer(Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                menus.forEach { menu ->
                    item {
                        ItemMenu(menu = menu) {

                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemMenu(
    modifier: Modifier = Modifier,
    menu: HomeAdapter.ItemMenu,
    clickOnItem: () -> Unit
) {
    Card(
        modifier = modifier
            .height(160.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        onClick = clickOnItem
    ) {
        Column {
            Image(
                contentDescription = null,
                painter = painterResource(menu.image),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Text(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(menu.title),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen(
        menus = listOf(
            HomeAdapter.ItemMenu(
                image = R.drawable.films,
                title = R.string.unknown
            ),
            HomeAdapter.ItemMenu(
                image = R.drawable.films,
                title = R.string.unknown
            ),
            HomeAdapter.ItemMenu(
                image = R.drawable.films,
                title = R.string.unknown
            ),
            HomeAdapter.ItemMenu(
                image = R.drawable.films,
                title = R.string.unknown
            )
        )
    )
}