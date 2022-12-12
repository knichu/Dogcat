package com.godminq.dogcat.data.entity

import com.google.gson.annotations.SerializedName

data class AnimalImage(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("urls") val urls: AnimalImageUrl,
    @field:SerializedName("user") val user: AnimalImageUser
)
