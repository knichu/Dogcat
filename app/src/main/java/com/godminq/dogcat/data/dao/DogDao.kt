package com.godminq.dogcat.data.dao
/*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.godminq.dogcat.data.entity.Dog
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {

    // insert
    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogAll(dog: List<Dog>)

    // select query
    @Query("SELECT * FROM collected_dog")
    fun getAllDog(): Flow<List<Dog>>

    @Query("SELECT category_id FROM collected_dog")
    fun getAllDogCategory(): Flow<List<String>>

    @Query("SELECT id FROM collected_dog where like_check = :boolean")
    fun getSelectedDogLikeCheckList(boolean: Boolean): Flow<List<Long>>

    // update query
    @Query("UPDATE collected_dog SET like_check = :like_check WHERE id = :id")
    fun updateDogLikeCheck(id: Long, like_check: Boolean)

    @Query("UPDATE collected_dog SET image_id = :image_id WHERE id = :id")
    fun updateDogImageId(id: Long, image_id: String)

    @Query("UPDATE collected_dog SET image_url = :image_url WHERE id = :id")
    fun updateDogImageUrl(id: Long, image_url: String)

    @Query("UPDATE collected_dog SET date = :date WHERE id = :id")
    fun updateDogDate(id: Long, date: Long)

}


 */