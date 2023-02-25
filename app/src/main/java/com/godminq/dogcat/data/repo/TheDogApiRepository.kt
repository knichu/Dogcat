package com.godminq.dogcat.data.repo

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.godminq.dogcat.api.TheDogApiService
import com.godminq.dogcat.data.TheDogApiPagingSource
import com.godminq.dogcat.data.entity.TheDogApiSearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TheDogApiRepository @Inject constructor(private val service: TheDogApiService) {

    fun getSearchResultStream(limit: Int): Flow<PagingData<TheDogApiSearchResponse>> {
        Log.d("태그", "getSearchResultStream1")
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { TheDogApiPagingSource(service, limit) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}
