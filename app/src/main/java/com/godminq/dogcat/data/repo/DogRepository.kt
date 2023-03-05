package com.godminq.dogcat.data.repo

import com.godminq.dogcat.data.dao.DogDao
import com.godminq.dogcat.data.entity.Dog
import com.godminq.dogcat.data.entity.TheDogApiSearchResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepository @Inject constructor(private val dogDao: DogDao) {

    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
//    suspend fun insertDogAll(dog: List<Dog>) = dogDao.insertDogAll(dog)

    fun getAllDog() = dogDao.getAllDog()

    fun getAllDogUrl() = dogDao.getAllDogUrl()

    suspend fun insertDog(dog: Dog) = dogDao.insertDog(dog)

    suspend fun deleteDog(dog: Dog) = dogDao.deleteDog(dog)

    suspend fun insertDogData(theDogApiSearchResponse: TheDogApiSearchResponse) {
        val id = theDogApiSearchResponse.id
        val url = theDogApiSearchResponse.url
        val width = theDogApiSearchResponse.width
        val height = theDogApiSearchResponse.height
        val dogData = Dog(id, url, width, height)
        dogDao.insertDogData(dogData)
    }


    companion object {

        // For Singleton instantiation
        @Volatile private var instance: DogRepository? = null

        fun getInstance(dogDao: DogDao) =
            instance ?: synchronized(this) {
                instance ?: DogRepository(dogDao).also { instance = it }
            }
    }
}