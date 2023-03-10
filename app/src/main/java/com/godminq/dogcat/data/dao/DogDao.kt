package com.godminq.dogcat.data.dao

import android.util.Log
import androidx.room.*
import com.godminq.dogcat.data.entity.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

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

    @Query("SELECT * FROM collected_dog WHERE id = :id")
    fun getDogId(id: String): Flow<Dog>?

    @Query("SELECT COUNT(*) FROM (SELECT id FROM collected_dog) WHERE id = :id")
    fun getNumOfDogId(id: String): Flow<Int>

    @Delete
    suspend fun deleteDog(dog: Dog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogData(dog: Dog)

    @Transaction
    suspend fun insertDogDataTest(dog: Dog) {
        Log.d("태그", "data4.0 = ")
        val existingDogData = getNumOfDogId(dog.id!!)
        Log.d("태그", "data4.1 = $existingDogData")
        existingDogData.collectLatest {
            Log.d("태그", "data4.2 = $it")
            if (it == 0) {
                insertDogData(dog)
            } else {
                // 중복된 데이터가 이미 1개 이상 존재하므로 삽입하지 않습니다.
            }
        }
    }
}