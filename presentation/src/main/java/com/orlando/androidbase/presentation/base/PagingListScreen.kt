package com.orlando.androidbase.presentation.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.orlando.androidbase.presentation.extensions.LoadState

@Composable
fun <T : Any> PagingListScreen(
    items: LazyPagingItems<T>,
    key: (T) -> Any,
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(
            count = items.itemCount,
            key = { index -> items[index]?.let { key(it) } ?: index }
        ) { index ->
            items[index]?.let { item ->
                itemContent(item)
            }
        }
        item {
            items.LoadState(Modifier.fillParentMaxSize())
        }
    }
}