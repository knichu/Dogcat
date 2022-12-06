/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.godminq.dogcat.data.repo
/*
import com.godminq.dogcat.data.dao.CatDao
import com.godminq.dogcat.data.entity.Cat
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [PlantDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
class CatRepository @Inject constructor(private val catDao: CatDao) {

    //insert
    //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
    suspend fun insertCatAll(cat: List<Cat>) = catDao.insertCatAll(cat)

    //select
    fun getAllCat() = catDao.getAllCat()

    fun getAllCatCategory() = catDao.getAllCatCategory()
    // false 값으로 접근
    fun getSelectedCatLikeCheckList(boolean: Boolean) = catDao.getSelectedCatLikeCheckList(boolean)

    //update
    fun updateCatLikeCheck(id: Long, like_check: Boolean) = catDao.updateCatLikeCheck(id, like_check)

    fun updateCatImageId(id: Long, image_id: String) = catDao.updateCatImageId(id, image_id)

    fun updateCatImageUrl(id: Long, image_url: String) = catDao.updateCatImageUrl(id, image_url)

    fun updateCatDate(id: Long, date: Long) = catDao.updateCatDate(id, date)


    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CatRepository? = null

        fun getInstance(catDao: CatDao) =
            instance ?: synchronized(this) {
                instance ?: CatRepository(catDao).also { instance = it }
            }
    }
}


 */