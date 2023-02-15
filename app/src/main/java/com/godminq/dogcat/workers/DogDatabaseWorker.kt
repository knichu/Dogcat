package com.godminq.dogcat.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.godminq.dogcat.data.AppDatabase
import com.godminq.dogcat.data.entity.Dog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.d("태그", "dog db ss1")
        try {
            Log.d("태그", "dog db ss2")
            val filename = inputData.getString(KEY_FILENAME_DOG)
            if (filename != null) {
                Log.d("태그", "dog db ss3")
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        Log.d("태그", "dog db ss4")

                        val dogType = object : TypeToken<List<Dog>>() {}.type
                        Log.d("태그", "dog db ss5 $dogType")
                        val dogList: List<Dog> = Gson().fromJson(jsonReader, dogType)
                        Log.d("태그", "dog db ss6 $dogList")
                        val databaseDog = AppDatabase.getInstance(applicationContext)
                        Log.d("태그", "dog db ss7")

                        //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
                        databaseDog.dogDao().insertDogAll(dogList)
                        Log.d("태그", "dog db ss6")

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG_DOG, "Error database - no valid filename")
                Log.d("태그", "dog Error database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG_DOG, "Error database", ex)
            Log.d("태그", "dog Error database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG_DOG = "DogDatabaseWorker"
        const val KEY_FILENAME_DOG = "DOG_DATA_FILENAME"
    }
}