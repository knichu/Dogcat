package com.godminq.dogcat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.godminq.dogcat.data.entity.Dog
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

@Dao
interface DogDao {

    // insert
    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogAll(dog: List<Dog>)

    // select query
    @Query("SELECT * FROM collected_dog")
    fun getAllDog(): Flow<List<Dog>>

    // 쓸일없을듯?
    @Query("SELECT categoryId FROM collected_dog")
    fun getAllDogCategory(): Flow<List<Long>>

    @Query("SELECT id FROM collected_dog WHERE likeCheck = :likeCheck")
    fun getSelectedDogLikeCheckList(likeCheck: Boolean): Flow<List<Long>>

    // 쿼리 완성하기
    @Query("SELECT categoryId FROM collected_dog WHERE id % 10 == 0")
    fun getAllDogCategories(): Flow<List<Long>>

    // update query
    @Query("UPDATE collected_dog SET likeCheck = :likeCheck WHERE id = :id")
    suspend fun updateDogLikeCheck(id: Long, likeCheck: Boolean)

    @Query("UPDATE collected_dog SET imageId = :imageId WHERE id = :id")
    suspend fun updateDogImageId(id: Long, imageId: String)

    @Query("UPDATE collected_dog SET imageUrl = :imageUrl WHERE id = :id")
    suspend fun updateDogImageUrl(id: Long, imageUrl: String)

    @Query("UPDATE collected_dog SET date = :date WHERE id = :id")
    suspend fun updateDogDate(id: Long, date: Calendar)
}