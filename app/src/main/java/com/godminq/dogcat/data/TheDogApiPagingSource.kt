package com.godminq.dogcat.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.godminq.dogcat.api.TheDogApiService
import com.godminq.dogcat.data.entity.TheDogApiSearchResponse

private const val THE_DOG_API_STARTING_PAGE_INDEX = 1

class TheDogApiPagingSource(
    private val service: TheDogApiService,
    private val limit: Int
) : PagingSource<Int, TheDogApiSearchResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TheDogApiSearchResponse> {
        Log.d("태그", "TheDogApiPagingSource1")
        val page = params.key ?: THE_DOG_API_STARTING_PAGE_INDEX
        val mimeType = "jpg"
        return try {
            Log.d("태그", "TheDogApiPagingSource2")
            val images = service.searchImages(limit, mimeType, page)
            Log.d("태그", "$images, try testing")
            Log.d("태그", "TheDogApiPagingSource3")
            LoadResult.Page(
                data = images,
                prevKey = if (page == THE_DOG_API_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == 100) null else page + 1
            )

        } catch (exception: Exception) {
//            val error123 =
//            Log.d("태그", "$error123, catch testing")
            LoadResult.Error(exception)

        }
    }

    override fun getRefreshKey(state: PagingState<Int, TheDogApiSearchResponse>): Int? {
        Log.d("태그", "TheDogApiPagingSource getRefreshKey1")
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            Log.d("태그", "TheDogApiPagingSource getRefreshKey2")
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
