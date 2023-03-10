package com.godminq.dogcat.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.godminq.dogcat.data.entity.TheDogApiSearchResponse
import com.godminq.dogcat.data.repo.DogRepository
import com.godminq.dogcat.data.repo.TheDogApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayDogTabViewModel @Inject constructor(
    private val theDogApiRepository: TheDogApiRepository,
    private val dogRepository: DogRepository
) : ViewModel() {

    private var currentQueryValue: Int? = null
    private var currentSearchResult: Flow<PagingData<TheDogApiSearchResponse>>? = null
    fun searchDogPictures(limit: Int): Flow<PagingData<TheDogApiSearchResponse>> {
        Log.d("태그", "searchDogPictures1")
        currentQueryValue = limit
        Log.d("태그", "searchDogPictures2")
        val newResult: Flow<PagingData<TheDogApiSearchResponse>> =
            theDogApiRepository.getSearchResultStream(limit).cachedIn(viewModelScope)
        Log.d("태그", "searchDogPictures3")
        currentSearchResult = newResult
        Log.d("태그", "searchDogPictures4")
        return newResult
    }

    val snap = PagerSnapHelper()
    fun getCurrentItemPosition(recyclerView: RecyclerView): Int {
        val snapView = snap.findSnapView(recyclerView.layoutManager)
        return snapView?.let { recyclerView.layoutManager?.getPosition(it) } ?: RecyclerView.NO_POSITION
    }

    fun addImageDataToCollection(theDogApiSearchResponse: TheDogApiSearchResponse?) {
        viewModelScope.launch {
            Log.d("태그", "addImageDataToCollection")
            if (theDogApiSearchResponse != null) {
                dogRepository.insertDogData(theDogApiSearchResponse)
            }
        }
    }

    fun checkNumOfDogId(id: String): Flow<Int> {
        return dogRepository.getNumOfDogId(id)
    }

}