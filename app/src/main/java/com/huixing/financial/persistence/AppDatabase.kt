package com.huixing.financial.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.huixing.financial.model.BaseFundData

@Database(entities = [BaseFundData::class], version = 2, exportSchema = true)
//@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun baseFundDao(): FundDao
}
