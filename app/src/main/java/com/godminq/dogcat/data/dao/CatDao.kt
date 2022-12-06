package com.godminq.dogcat.data.dao
/*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.godminq.dogcat.data.entity.Cat
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    // insert
    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatAll(cat: List<Cat>)

    // select query
    @Query("SELECT * FROM collected_cat")
    fun getAllCat(): Flow<List<Cat>>

    @Query("SELECT category_id FROM collected_cat")
    fun getAllCatCategory(): Flow<List<String>>

    @Query("SELECT id FROM collected_cat where like_check = :boolean")
    fun getSelectedCatLikeCheckList(boolean: Boolean): Flow<List<Long>>

    // update query
    @Query("UPDATE collected_cat SET like_check = :like_check WHERE id = :id")
    fun updateCatLikeCheck(id: Long, like_check: Boolean)

    @Query("UPDATE collected_cat SET image_id = :image_id WHERE id = :id")
    fun updateCatImageId(id: Long, image_id: String)

    @Query("UPDATE collected_cat SET image_url = :image_url WHERE id = :id")
    fun updateCatImageUrl(id: Long, image_url: String)

    @Query("UPDATE collected_cat SET date = :date WHERE id = :id")
    fun updateCatDate(id: Long, date: Long)
}

 */