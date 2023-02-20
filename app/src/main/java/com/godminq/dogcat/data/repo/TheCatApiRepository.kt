package com.godminq.dogcat.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.godminq.dogcat.api.TheCatApiService
import com.godminq.dogcat.data.TheCatApiPagingSource
import com.godminq.dogcat.data.entity.TheCatApiSearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TheCatApiRepository @Inject constructor(private val service: TheCatApiService) {

    fun getSearchResultStream(limit: Int): Flow<PagingData<TheCatApiSearchResponse>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { TheCatApiPagingSource(service, limit) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}
