package com.example.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState

class BasePagingSource<V : Any>(
    private val block: suspend (Int) -> List<V>
) : PagingSource<Int, V>() {

    companion object {
        private const val START_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
        val currentPage = params.key ?: START_PAGE
        val data = block.invoke(currentPage)
        return try {
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == START_PAGE) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}