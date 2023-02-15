package com.godminq.dogcat.data.entity

import androidx.room.*
import java.util.Calendar

@Entity(tableName = "collected_dog")
class Dog() : Animal() {

    @PrimaryKey(autoGenerate = true) @ColumnInfo
    var id: Long = 1

    @ColumnInfo
    var categoryId: Long? = 0

    @ColumnInfo
    var categoryName: String = ""

    @Embedded
    var dogImage: DogImage? = null

    override fun toString(): String {
        return "id = $id, categoryId = $categoryId, categoryName = $categoryName"
    }

    override fun returnAnimalType(): String = "Dog"
}

class DogImage () {

    @ColumnInfo
    var likeCheck: Boolean = false

    @ColumnInfo
    var imageId: String? = null

    @ColumnInfo
    var imageUrl: String? = null

    @ColumnInfo(name = "date")
    var dateTime: Calendar? = Calendar.getInstance()
}
