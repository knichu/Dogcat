package com.godminq.dogcat.viewmodels

import androidx.lifecycle.*
import com.godminq.dogcat.adapters.CollectionCatAdapter
import com.godminq.dogcat.adapters.CollectionDogAdapter
import com.godminq.dogcat.data.entity.Animal
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import com.godminq.dogcat.data.repo.CatRepository
import com.godminq.dogcat.data.repo.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DogAndCatCollectionViewModel @Inject constructor(
    private val dogRepository : DogRepository,
    private val catRepository : CatRepository,
): ViewModel() {

    val dogRepo: DogRepository = dogRepository
    val catRepo: CatRepository = catRepository

    val dogRepoGetAllDog: LiveData<List<Dog>> = dogRepository.getAllDog().asLiveData()
    val catRepoGetAllCat: LiveData<List<Cat>> = catRepository.getAllCat().asLiveData()

    private val _animal: MutableLiveData<Animal> = MutableLiveData()
    val animal: LiveData<Animal> = _animal

    init {
        _animal.value = Dog()
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

