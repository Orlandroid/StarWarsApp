package com.orlando.androidbase.presentation.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orlando.androidbase.R
import com.orlando.androidbase.presentation.features.components.ImageBackgroundScreen
import com.orlando.androidbase.presentation.features.components.ImageBackgroundScreenConfig


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    menus: List<HomeAdapter.ItemMenu>,
    onEvents: (HomeScreenEvents) -> Unit
) {
    ImageBackgroundScreen(
        imageBackgroundScreenConfig = ImageBackgroundScreenConfig(image = R.drawable.stars_bg_full)
    ) {
        Column(modifier.fillMaxWidth()) {
            Image(
                contentDescription = "StarWarsLogo",
                painter = painterResource(R.drawable.startwars),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                menus.forEach { menu ->
                    item {
                        ItemMenu(menu = menu) {
                            onMenuClicked(
                                menu = menu, onEvents = onEvents
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun onMenuClicked(
    menu: HomeAdapter.ItemMenu,
    onEvents: (HomeScreenEvents) -> Unit
) {
    when (menu.menuName) {
        HomeAdapter.MenuName.CHARACTERS -> {
            onEvents(HomeScreenEvents.OnClickOnCharacters)
        }

        HomeAdapter.MenuName.MOVIES -> {
            onEvents(HomeScreenEvents.OnClickOnMovies)
        }

        HomeAdapter.MenuName.PLANETS -> {
            onEvents(HomeScreenEvents.OnClickOnPlanets)
        }

        HomeAdapter.MenuName.SPECIES -> {
            onEvents(HomeScreenEvents.OnClickOnSpecies)
        }

        HomeAdapter.MenuName.STARSHIPS -> {
            onEvents(HomeScreenEvents.OnClickOnStarShips)
        }

        HomeAdapter.MenuName.VEHICLES -> {
            onEvents(HomeScreenEvents.OnClickOnVehicles)
        }
    }
}

@Composable
private fun ItemMenu(
    modifier: Modifier = Modifier,
    menu: HomeAdapter.ItemMenu, clickOnItem: () -> Unit
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
        modifier = Modifier,
        menus = listOf(
            HomeAdapter.ItemMenu(
                image = R.drawable.films,
                title = R.string.unknown,
                menuName = HomeAdapter.MenuName.VEHICLES
            ),
            HomeAdapter.ItemMenu(
                image = R.drawable.films,
                title = R.string.unknown,
                menuName = HomeAdapter.MenuName.VEHICLES
            ),
            HomeAdapter.ItemMenu(
                image = R.drawable.films,
                title = R.string.unknown,
                menuName = HomeAdapter.MenuName.VEHICLES
            ),
            HomeAdapter.ItemMenu(
                image = R.drawable.films,
                title = R.string.unknown,
                menuName = HomeAdapter.MenuName.VEHICLES
            )
        ),
        onEvents = {}
    )

}