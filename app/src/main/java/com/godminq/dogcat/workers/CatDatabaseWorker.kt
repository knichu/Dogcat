package com.godminq.dogcat.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.godminq.dogcat.data.AppDatabase
import com.godminq.dogcat.data.entity.Cat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.d("태그", "cat db ss1")
        try {
            Log.d("태그", "cat db ss2")
            val filename = inputData.getString(KEY_FILENAME_CAT)
            if (filename != null) {
                Log.d("태그", "cat db ss3")
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        Log.d("태그", "cat db ss4")

                        val catType = object : TypeToken<List<Cat>>() {}.type
                        Log.d("태그", "cat db ss5 $catType")
                        val catList: List<Cat> = Gson().fromJson(jsonReader, catType)
                        Log.d("태그", "cat db ss6 $catList")
                        val databaseCat = AppDatabase.getInstance(applicationContext)
                        Log.d("태그", "cat db ss7")

                        //insertAll, 이거 이 Worker 구조 파악 못하고 임시로 넣음. 파악후 수정
                        databaseCat.catDao().insertCatAll(catList)
                        Log.d("태그", "cat db ss8")

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG_CAT, "Error database - no valid filename")
                Log.d("태그", "cat Error database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG_CAT, "Error database", ex)
            Log.d("태그", "cat Error database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG_CAT = "CatDatabaseWorker"
        const val KEY_FILENAME_CAT = "CAT_DATA_FILENAME"
    }
}