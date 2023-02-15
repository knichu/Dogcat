package com.godminq.dogcat.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.godminq.dogcat.data.entity.Animal
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import com.godminq.dogcat.data.entity.DogAndCat
import com.godminq.dogcat.data.repo.DogRepository
import java.text.SimpleDateFormat
import java.util.*

class CollectionAdapterViewModel(dogAndCat: DogAndCat) {
    private val dog = checkNotNull(dogAndCat.dog)
    private val cat = checkNotNull(dogAndCat.cat)

    private val _animal: MutableLiveData<Animal> = MutableLiveData()
    val animal: LiveData<Animal> = _animal

    // collection
    val dogId
        get() = dog.id
    val dogCategoryId
        get() = dog.categoryId
    val dogCategoryName
        get() = dog.categoryName

    val catId
        get() = cat.id
    val catCategoryId
        get() = cat.categoryId
    val catCategoryName
        get() = cat.categoryName

    val animalType
        get() = _animal.value?.returnAnimalType()

    val animalId
        get() = _animal.value?.returnAnimalType()

    fun setAnimalType(animalType : String?) {
        when(animalType) {
            "Dog" -> {
                _animal.value = Dog()
//                _animalIcon.value = Dog()
//                _animalListItem.value = Dog()
            }
            "Cat" -> {
                _animal.value = Cat()
//                _animalIcon.value = Cat()
//                _animalListItem.value = Cat()
            }
        }
    }




    // detail collection


    val likingDogDateString: String? = dog.dogImage?.dateTime?.time?.let { dateFormat.format(it) }


    val wateringInterval
        get() = plant.wateringInterval
    val imageUrl
        get() = plant.imageUrl
    val plantName
        get() = plant.name
    val plantDateString: String = dateFormat.format(gardenPlanting.plantDate.time)
    val plantId
        get() = plant.plantId

    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}