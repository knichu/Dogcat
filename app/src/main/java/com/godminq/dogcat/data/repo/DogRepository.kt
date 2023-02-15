package com.godminq.dogcat.data.repo

import com.godminq.dogcat.data.dao.DogDao
import com.godminq.dogcat.data.entity.Dog
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepository @Inject constructor(private val dogDao: DogDao) {

    //insert
    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
//    suspend fun insertDogAll(dog: List<Dog>) = dogDao.insertDogAll(dog)

    //select
    fun getAllDog() = dogDao.getAllDog()

    fun getAllDogCategory() = dogDao.getAllDogCategory()
    // false 값으로 접근
    fun getSelectedDogLikeCheckList(boolean: Boolean) = dogDao.getSelectedDogLikeCheckList(boolean)

    //update
    suspend fun updateDogLikeCheck(id: Long, like_check: Boolean) = dogDao.updateDogLikeCheck(id, like_check)

    suspend fun updateDogImageId(id: Long, image_id: String) = dogDao.updateDogImageId(id, image_id)

    suspend fun updateDogImageUrl(id: Long, image_url: String) = dogDao.updateDogImageUrl(id, image_url)

    suspend fun updateDogDate(id: Long, date: Calendar) = dogDao.updateDogDate(id, date)


    companion object {

        // For Singleton instantiation
        @Volatile private var instance: DogRepository? = null

        fun getInstance(dogDao: DogDao) =
            instance ?: synchronized(this) {
                instance ?: DogRepository(dogDao).also { instance = it }
            }
    }
}