package com.godminq.dogcat.data.entity

import androidx.room.*
import java.util.Calendar

@Entity(tableName = "collected_dog")
data class Dog(
    @ColumnInfo
    val id: String? = "",

    @ColumnInfo
    val url: String? = "",

    @ColumnInfo
    val width: Long? = 0,

    @ColumnInfo
    val height: Long? = 0,

    @ColumnInfo(name = "date")
    val dateTime: Calendar? = Calendar.getInstance()

) : Animal() {

    @PrimaryKey(autoGenerate = true) @ColumnInfo
    var primaryId: Long = 0

    override fun toString(): String {
        return "primaryId = $primaryId, id = $id, url = $url"
    }

    override fun returnAnimalType(): String = "Dog"
}
