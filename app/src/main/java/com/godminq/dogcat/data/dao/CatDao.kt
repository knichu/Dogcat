package com.godminq.dogcat.data.dao

import android.util.Log
import androidx.room.*
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
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

    @Query("SELECT * FROM collected_cat WHERE id = :id")
    fun getCatId(id: String): Flow<Cat>?

    @Query("SELECT COUNT(*) FROM (SELECT id FROM collected_cat) WHERE id = :id")
    fun getNumOfCatId(id: String): Flow<Int>

    @Delete
    suspend fun deleteCat(cat: Cat)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatData(cat: Cat)

    @Transaction
    suspend fun insertCatDataTest(cat: Cat) {
        Log.d("태그", "data4.0 = ")
        val existingCatData = getNumOfCatId(cat.id!!)
        Log.d("태그", "data4.1 = $existingCatData")
        existingCatData.collectLatest {
            Log.d("태그", "data4.2 = $it")
            if (it == 0) {
                insertCatData(cat)
            } else {
                // 중복된 데이터가 이미 1개 이상 존재하므로 삽입하지 않습니다.
            }
        }
    }
}