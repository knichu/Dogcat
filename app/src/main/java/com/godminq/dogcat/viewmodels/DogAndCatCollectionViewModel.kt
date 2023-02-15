package com.godminq.dogcat.viewmodels

import android.media.Image
import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.godminq.dogcat.data.entity.Animal
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import com.godminq.dogcat.data.entity.DogAndCat
import com.godminq.dogcat.data.repo.CatRepository
import com.godminq.dogcat.data.repo.DogRepository
import com.godminq.dogcat.data.repo.TheCatApiRepository
import com.godminq.dogcat.data.repo.TheDogApiRepository
import com.godminq.dogcat.ui.collection.DogAndCatCollectionFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DogAndCatCollectionViewModel @Inject constructor(
    private val dogRepository : DogRepository,
    private val catRepository : CatRepository,
    private val theDogApiRepository: TheDogApiRepository,
    private val theCatApiRepository: TheCatApiRepository
): ViewModel() {

    private val _animal: MutableLiveData<Animal> = MutableLiveData()
    val animal: LiveData<Animal> = _animal

//    private val _animalIcon: MutableLiveData<Image> = MutableLiveData()
//    val animalIcon: LiveData<Image> = _animalIcon
//
//    private val _animalListItem: MutableLiveData<List<Int>> = MutableLiveData()
//    val animalListItem: LiveData<List<Int>> = _animalListItem

    private val _likes: MutableLiveData<Int> = MutableLiveData()
    val likes: LiveData<Int> = _likes


    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<DogAndCat>>? = null

    fun searchPictures(queryString: String): Flow<PagingData<DogAndCat>> {
        currentQueryValue = queryString
        if (queryString == "Dog") {
            val newResult: Flow<PagingData<DogAndCat>> =
                theDogApiRepository.getSearchResultStream(queryString).cachedIn(viewModelScope)
            currentSearchResult = newResult
            return newResult
        }
        //Cat
        else {
            val newResult: Flow<PagingData<DogAndCat>> =
                theCatApiRepository.getSearchResultStream(queryString).cachedIn(viewModelScope)
            currentSearchResult = newResult
            return newResult
        }
    }


    //test
    val getTestDog: LiveData<List<Dog>> = dogRepository.getAllDog().asLiveData()

    init {
        Log.d("태그", "viewModel1")
        _animal.value = Dog()
        Log.d("태그", "viewModel2")
    }

    fun setAnimalType(animalType : String?) {
        when(animalType) {
            "Dog" -> {
                _animal.value = Dog()
            }
            "Cat" -> {
                _animal.value = Cat()
            }
        }
    }


}

