package com.godminq.dogcat.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.godminq.dogcat.data.dao.DogDao
import com.godminq.dogcat.data.entity.Animal
import com.godminq.dogcat.data.entity.Dog
import com.godminq.dogcat.data.repo.DogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CollectionDogAdapterViewModel(dog: Dog) {

    private val dog = dog

    private val _animal: MutableLiveData<Animal> = MutableLiveData()
    val animal: LiveData<Animal> = _animal

    val likingDogDateString: String? = dog.dateTime?.time?.let { dateFormat.format(it) }

    val animalType
        get() = _animal.value?.returnAnimalType()

    val animalId
        get() = dog.id

    val animalUrl
        get() = dog.url

    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}