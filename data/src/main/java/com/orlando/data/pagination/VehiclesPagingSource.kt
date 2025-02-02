package com.orlando.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orlando.androidbase.entities.remote.ResultVehicle
import com.orlando.data.remote.ApiService
import retrofit2.HttpException

class VehiclesPagingSource(
    private val service: ApiService
) : PagingSource<Int, ResultVehicle>() {

    companion object {
        private const val START_PAGE = 1
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ResultVehicle> {
        return try {
            /// Todo add mapping for data to domain class
            val currentPage = params.key ?: START_PAGE
            val data = service.getVehicles(currentPage.toString()).results
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == START_PAGE) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage.plus(1)
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

    override fun getRefreshKey(state: PagingState<Int, ResultVehicle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
