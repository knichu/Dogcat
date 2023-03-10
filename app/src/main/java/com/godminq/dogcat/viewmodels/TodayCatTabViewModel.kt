package com.godminq.dogcat.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.godminq.dogcat.data.entity.TheCatApiSearchResponse
import com.godminq.dogcat.data.repo.CatRepository
import com.godminq.dogcat.data.repo.TheCatApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayCatTabViewModel @Inject constructor(
    private val theCatApiRepository: TheCatApiRepository,
    private val catRepository: CatRepository
) : ViewModel() {

    private var currentQueryValue: Int? = null
    private var currentSearchResult: Flow<PagingData<TheCatApiSearchResponse>>? = null
    fun searchCatPictures(limit: Int): Flow<PagingData<TheCatApiSearchResponse>> {
        currentQueryValue = limit
        val newResult: Flow<PagingData<TheCatApiSearchResponse>> =
            theCatApiRepository.getSearchResultStream(limit).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    val snap = PagerSnapHelper()
    fun getCurrentItemPosition(recyclerView: RecyclerView): Int {
        val snapView = snap.findSnapView(recyclerView.layoutManager)
        return snapView?.let { recyclerView.layoutManager?.getPosition(it) } ?: RecyclerView.NO_POSITION
    }

    fun addImageDataToCollection(theCatApiSearchResponse: TheCatApiSearchResponse?) {
        viewModelScope.launch {
            if (theCatApiSearchResponse != null) {
                catRepository.insertCatData(theCatApiSearchResponse)
            }
        }
    }

    fun checkNumOfCatId(id: String): Flow<Int> {
        return catRepository.getNumOfCatId(id)
    }

}