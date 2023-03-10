package com.godminq.dogcat.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.godminq.dogcat.api.TheCatApiService
import com.godminq.dogcat.data.entity.TheCatApiSearchResponse

private const val THE_CAT_API_STARTING_PAGE_INDEX = 1

class TheCatApiPagingSource(
    private val service: TheCatApiService,
    private val limit: Int
) : PagingSource<Int, TheCatApiSearchResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TheCatApiSearchResponse> {
        val page = params.key ?: THE_CAT_API_STARTING_PAGE_INDEX
        val mimeType = "jpg"
        return try {
            val images = service.searchImages(limit, mimeType, page)
            LoadResult.Page(
                data = images,
                prevKey = if (page == THE_CAT_API_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == 100) null else page + 1
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TheCatApiSearchResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
