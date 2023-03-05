package com.godminq.dogcat.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.godminq.dogcat.data.entity.Animal
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodayViewPagerViewModel @Inject constructor(
//    private val dogRepository : DogRepository,
//    private val catRepository : CatRepository,
//    private val theDogApiRepository: TheDogApiRepository,
//    private val theCatApiRepository: TheCatApiRepository
): ViewModel() {

    private val _animal: MutableLiveData<Animal> = MutableLiveData()
    val animal: LiveData<Animal> = _animal

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