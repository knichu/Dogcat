package com.godminq.dogcat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.godminq.dogcat.data.entity.Cat
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

@Dao
interface CatDao {

    // insert
    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatAll(cat: List<Cat>)

    // select query
    @Query("SELECT * FROM collected_cat")
    fun getAllCat(): Flow<List<Cat>>

    @Query("SELECT categoryId FROM collected_cat")
    fun getAllCatCategory(): Flow<List<String>>

    @Query("SELECT id FROM collected_cat where likeCheck = :likeCheck")
    fun getSelectedCatLikeCheckList(likeCheck: Boolean): Flow<List<Long>>

    // update query
    @Query("UPDATE collected_cat SET likeCheck = :likeCheck WHERE id = :id")
    suspend fun updateCatLikeCheck(id: Long, likeCheck: Boolean)

    @Query("UPDATE collected_cat SET imageId = :imageId WHERE id = :id")
    suspend fun updateCatImageId(id: Long, imageId: String)

    @Query("UPDATE collected_cat SET imageUrl = :imageUrl WHERE id = :id")
    suspend fun updateCatImageUrl(id: Long, imageUrl: String)

    @Query("UPDATE collected_cat SET date = :date WHERE id = :id")
    suspend fun updateCatDate(id: Long, date: Calendar)
}