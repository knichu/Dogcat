package com.godminq.dogcat.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.godminq.dogcat.data.converter.Converters
import com.godminq.dogcat.data.dao.CatDao
import com.godminq.dogcat.data.dao.DogDao
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import com.godminq.dogcat.utilites.CAT_DATA_FILENAME
import com.godminq.dogcat.utilites.DATABASE_NAME
import com.godminq.dogcat.utilites.DOG_DATA_FILENAME

@Database(entities = [Dog::class, Cat::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
    abstract fun catDao(): CatDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            Log.d("태그", "SS1")
            return instance ?: synchronized(this) {
                Log.d("태그", "SS2")
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        // 잘 안되면 .addCallback 블록하고 DogcatDatabaseWorker.kt 지워보기
        // + addcallback 부분은 필수가 아닌듯?
        private fun buildDatabase(context: Context): AppDatabase {
            Log.d("태그", "SS3")
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
//                .addCallback(
//                    object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            Log.d("태그", "SS4")
//                            val dogRequest = OneTimeWorkRequestBuilder<DogDatabaseWorker>()
//                                .setInputData(workDataOf(KEY_FILENAME_DOG to DOG_DATA_FILENAME))
//                                .build()
//                            WorkManager.getInstance(context).enqueue(dogRequest)
//                            Log.d("태그", "SS5")
//
//                            val catRequest = OneTimeWorkRequestBuilder<CatDatabaseWorker>()
//                                .setInputData(workDataOf(KEY_FILENAME_CAT to CAT_DATA_FILENAME))
//                                .build()
//                            WorkManager.getInstance(context).enqueue(catRequest)
//                            Log.d("태그", "SS6")
//
//                        }
//                    }
//                )
                // 임시로 추가
//                .allowMainThreadQueries()

                .build()
        }
    }
}