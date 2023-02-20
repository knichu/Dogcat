package com.godminq.dogcat.data.repo

import com.godminq.dogcat.data.dao.CatDao
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepository @Inject constructor(private val catDao: CatDao) {

    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
//    suspend fun insertCatAll(cat: List<Cat>) = catDao.insertCatAll(cat)

    fun getAllCat() = catDao.getAllCat()

    fun getAllCatUrl() = catDao.getAllCatUrl()

    suspend fun insertCat(cat: Cat) = catDao.insertCat(cat)

    suspend fun deleteCat(cat: Cat) = catDao.deleteCat(cat)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CatRepository? = null

        fun getInstance(catDao: CatDao) =
            instance ?: synchronized(this) {
                instance ?: CatRepository(catDao).also { instance = it }
            }
    }
}