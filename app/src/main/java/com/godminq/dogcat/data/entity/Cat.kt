package com.godminq.dogcat.data.entity

import androidx.room.*
import java.util.Calendar

@Entity(tableName = "collected_cat")
class Cat() : Animal() {

    @PrimaryKey(autoGenerate = true) @ColumnInfo
    var id: Long = 1

    @ColumnInfo
    var categoryId: String? = ""

    @ColumnInfo
    var categoryName: String = ""

    @Embedded
    var catImage: CatImage? = null

    override fun toString(): String {
        return "id = $id, categoryId = $categoryId, categoryName = $categoryName"
    }

//    override var animalType : String? = "Cat"

    override fun returnAnimalType(): String = "Cat"


}

class CatImage () {

    @ColumnInfo
    var likeCheck: Boolean = false

    @ColumnInfo
    var imageId: String? = null

    @ColumnInfo
    var imageUrl: String? = null

    @ColumnInfo(name = "date")
    var dateTime: Calendar? = Calendar.getInstance()
}
