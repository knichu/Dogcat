package com.godminq.dogcat.data.entity

import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable

data class TheDogApiSearchResponse (
    @field:SerializedName("breeds") val breeds: List<Breeds?>,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("width") val width: Long,
    @field:SerializedName("height") val height: Long
)
