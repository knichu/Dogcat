package com.godminq.dogcat.data.entity

import androidx.room.*
import java.util.Calendar

@Entity(tableName = "collected_cat")
data class Cat(
    @ColumnInfo
    var id: String? = "",

    @ColumnInfo
    var url: String? = "",

    @ColumnInfo
    var width: Long? = 0,

    @ColumnInfo
    var height: Long? = 0,

    @ColumnInfo(name = "date")
    var dateTime: Calendar? = Calendar.getInstance()

) : Animal() {

    @PrimaryKey(autoGenerate = true) @ColumnInfo
    var primaryId: Long = 1

//    @ColumnInfo
//    var id: String? = ""
//
//    @ColumnInfo
//    var url: String? = ""
//
//    @ColumnInfo
//    var width: Long? = 0
//
//    @ColumnInfo
//    var height: Long? = 0
//
//    @ColumnInfo(name = "date")
//    var dateTime: Calendar? = Calendar.getInstance()

    override fun toString(): String {
        return "primaryId = $primaryId, id = $id, url = $url"
    }

    override fun returnAnimalType(): String = "Cat"
}
