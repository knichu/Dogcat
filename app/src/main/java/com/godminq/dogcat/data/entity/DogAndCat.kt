package com.godminq.dogcat.data.entity

import androidx.room.Embedded

data class DogAndCat (
    @Embedded
    val dog: Dog,

    @Embedded
    val cat: Cat,
)