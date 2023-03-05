package com.godminq.dogcat.data.dao

import androidx.room.*
import com.godminq.dogcat.data.entity.Dog
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {
    // insert
    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogAll(dog: List<Dog>)

    @Query("SELECT * FROM collected_dog")
    fun getAllDog(): Flow<List<Dog>>

    @Query("SELECT url FROM collected_dog")
    fun getAllDogUrl(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: Dog): Long

    @Delete
    suspend fun deleteDog(dog: Dog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogData(dog: Dog)
}