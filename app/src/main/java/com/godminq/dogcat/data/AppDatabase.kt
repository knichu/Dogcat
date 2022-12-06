package com.godminq.dogcat.data
/*
import android.content.Context
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
import com.godminq.dogcat.utilites.DATABASE_NAME
import com.godminq.dogcat.utilites.DOGCAT_DATA_FILENAME
import com.godminq.dogcat.workers.DogcatDatabaseWorker
import com.godminq.dogcat.workers.DogcatDatabaseWorker.Companion.KEY_FILENAME

@Database(entities = [Dog::class, Cat::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
    abstract fun catDao(): CatDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        // 잘 안되면 .addCallback 블록하고 DogcatDatabaseWorker.kt 지워보기
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<DogcatDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to DOGCAT_DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}





 */