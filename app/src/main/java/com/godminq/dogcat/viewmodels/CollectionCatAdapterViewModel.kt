package com.godminq.dogcat.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.godminq.dogcat.data.entity.Animal
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import java.text.SimpleDateFormat
import java.util.*

class CollectionCatAdapterViewModel(cat: Cat) {

    private val cat = cat

    private val _animal: MutableLiveData<Animal> = MutableLiveData()
    val animal: LiveData<Animal> = _animal

    val likingCatDateString: String? = cat.dateTime?.time?.let { dateFormat.format(it) }

    val animalType
        get() = _animal.value?.returnAnimalType()

    val animalId
        get() = cat.id

    val animalUrl
        get() = cat.url

    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}