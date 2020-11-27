package com.huixing.financial.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.huixing.financial.model.BaseFundData

@Database(entities = [BaseFundData::class], version = 2, exportSchema = true)
//@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun baseFundDao(): FundDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room
                    .databaseBuilder(context, AppDatabase::class.java, "financial.db")
                    .fallbackToDestructiveMigration()
                    .build()
        }


    }
}
