package com.orlando.androidbase.presentation.features.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orlando.androidbase.presentation.features.theme.StatusBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    navController: NavController,
    toolbarConfiguration: ToolbarConfiguration,
) {
    TopAppBar(
        colors =
            topAppBarColors(
                containerColor = toolbarConfiguration.toolbarBackgroundColor,
                titleContentColor = toolbarConfiguration.toolbarTextColor,
            ),
        title = {
            Text(
                toolbarConfiguration.title,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            if (toolbarConfiguration.isWithBackIcon) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            }
        }
    )
}

data class ToolbarConfiguration(
    val showToolbar: Boolean = true,
    val title: String = "Android Developer",
    val isWithBackIcon: Boolean = true,
    val toolbarBackgroundColor: Color = StatusBarColor,
    val toolbarTextColor: Color = Color.White,
    val clickOnBackButton: () -> Unit = {}
)


@Preview(showBackground = true)
@Composable
private fun SimpleComposablePreview() {
    Toolbar(
        navController = rememberNavController(),
        toolbarConfiguration = ToolbarConfiguration(title = "Android Developer"),
    )
}
