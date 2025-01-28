package com.orlando.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orlando.androidbase.entities.remote.Film
import com.orlando.data.remote.ApiService
import retrofit2.HttpException

class FilmsPagingSource(
    private val service: ApiService
) : PagingSource<Int, Film>() {

    companion object {
        private const val START_PAGE = 1
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Film> {
        return try {
            val currentPage = params.key ?: START_PAGE
            val data = service.getFilms(currentPage.toString())
            LoadResult.Page(
                data = data.results,
                prevKey = if (currentPage == START_PAGE) null else currentPage - 1,
                nextKey = if (data.next == null) null else currentPage.plus(1)
            )
        } catch (e: Exception) {
            if (e is HttpException) {
                val errorString =
                    e.response()?.errorBody()?.byteStream()?.bufferedReader().use { it?.readText() }
                LoadResult.Error(Throwable(errorString))
            } else {
                LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
