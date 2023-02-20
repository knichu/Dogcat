package com.godminq.dogcat.data.dao

import androidx.room.*
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

@Dao
interface CatDao {
    // insert
    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatAll(dog: List<Cat>)

    @Query("SELECT * FROM collected_cat")
    fun getAllCat(): Flow<List<Cat>>

    @Query("SELECT url FROM collected_cat")
    fun getAllCatUrl(): Flow<List<String>>

    @Insert
    suspend fun insertCat(cat: Cat): Long

    @Delete
    suspend fun deleteCat(cat: Cat)
}