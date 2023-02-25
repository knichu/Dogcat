package com.godminq.dogcat.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.godminq.dogcat.data.entity.TheDogApiSearchResponse
import com.godminq.dogcat.data.repo.TheDogApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TodayDogTabViewModel @Inject constructor(
    private val repository: TheDogApiRepository
) : ViewModel() {

    private var currentQueryValue: Int? = null
    private var currentSearchResult: Flow<PagingData<TheDogApiSearchResponse>>? = null

    fun searchDogPictures(limit: Int): Flow<PagingData<TheDogApiSearchResponse>> {
        Log.d("태그", "searchDogPictures1")
        currentQueryValue = limit
        Log.d("태그", "searchDogPictures2")
        val newResult: Flow<PagingData<TheDogApiSearchResponse>> =
            repository.getSearchResultStream(limit).cachedIn(viewModelScope)
        Log.d("태그", "searchDogPictures3")
        currentSearchResult = newResult
        Log.d("태그", "searchDogPictures4")
        return newResult
    }

}