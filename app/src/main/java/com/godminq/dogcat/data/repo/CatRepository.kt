package com.godminq.dogcat.data.repo

import com.godminq.dogcat.data.dao.CatDao
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import com.godminq.dogcat.data.entity.TheCatApiSearchResponse
import com.godminq.dogcat.data.entity.TheDogApiSearchResponse
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepository @Inject constructor(private val catDao: CatDao) {

    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
//    suspend fun insertCatAll(cat: List<Cat>) = catDao.insertCatAll(cat)

    fun getAllCat() = catDao.getAllCat()

    fun getAllCatUrl() = catDao.getAllCatUrl()

    fun getCatId(id: String) = catDao.getCatId(id)

    fun getNumOfCatId(id: String) = catDao.getNumOfCatId(id)

    suspend fun deleteCat(cat: Cat) = catDao.deleteCat(cat)

    suspend fun insertCatData(theCatApiSearchResponse: TheCatApiSearchResponse) {
        val id = theCatApiSearchResponse.id
        val url = theCatApiSearchResponse.url
        val width = theCatApiSearchResponse.width
        val height = theCatApiSearchResponse.height
        val catData = Cat(id, url, width, height)
        catDao.insertCatData(catData)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CatRepository? = null

        fun getInstance(catDao: CatDao) =
            instance ?: synchronized(this) {
                instance ?: CatRepository(catDao).also { instance = it }
            }
    }
}