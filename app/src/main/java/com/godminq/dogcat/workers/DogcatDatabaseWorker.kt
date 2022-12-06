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

package com.godminq.dogcat.workers
/*
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.godminq.dogcat.data.AppDatabase
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogcatDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->

                        val DogType = object : TypeToken<List<Dog>>() {}.type
                        val DogList: List<Dog> = Gson().fromJson(jsonReader, DogType)

                        val databaseDog = AppDatabase.getInstance(applicationContext)
                        //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
                        databaseDog.dogDao().insertDogAll(DogList)

                        val CatType = object : TypeToken<List<Cat>>() {}.type
                        val CatList: List<Cat> = Gson().fromJson(jsonReader, CatType)

                        val databaseCat = AppDatabase.getInstance(applicationContext)
                        //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
                        databaseCat.catDao().insertCatAll(CatList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "DogcatDatabaseWorker"
        const val KEY_FILENAME = "DOGCAT_DATA_FILENAME"
    }
}


 */