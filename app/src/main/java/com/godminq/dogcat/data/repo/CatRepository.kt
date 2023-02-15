package com.godminq.dogcat.data.repo

import com.godminq.dogcat.data.dao.CatDao
import com.godminq.dogcat.data.entity.Cat
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepository @Inject constructor(private val catDao: CatDao) {

    //insert
    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
//    suspend fun insertCatAll(cat: List<Cat>) = catDao.insertCatAll(cat)

    //select
    fun getAllCat() = catDao.getAllCat()

    fun getAllCatCategory() = catDao.getAllCatCategory()
    // false 값으로 접근
    fun getSelectedCatLikeCheckList(boolean: Boolean) = catDao.getSelectedCatLikeCheckList(boolean)

    //update
    suspend fun updateCatLikeCheck(id: Long, like_check: Boolean) = catDao.updateCatLikeCheck(id, like_check)

    suspend fun updateCatImageId(id: Long, image_id: String) = catDao.updateCatImageId(id, image_id)

    suspend fun updateCatImageUrl(id: Long, image_url: String) = catDao.updateCatImageUrl(id, image_url)

    suspend fun updateCatDate(id: Long, date: Calendar) = catDao.updateCatDate(id, date)


    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CatRepository? = null

        fun getInstance(catDao: CatDao) =
            instance ?: synchronized(this) {
                instance ?: CatRepository(catDao).also { instance = it }
            }
    }
}