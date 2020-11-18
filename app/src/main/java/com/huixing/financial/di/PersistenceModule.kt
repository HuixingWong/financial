package com.huixing.financial.di

import android.app.Application
import androidx.room.Room
import com.huixing.financial.persistence.AppDatabase
import com.huixing.financial.persistence.FundDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
            application: Application,
    ): AppDatabase {
        return Room
                .databaseBuilder(application, AppDatabase::class.java, "financial.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideBaseFundDao(appDatabase: AppDatabase): FundDao {
        return appDatabase.baseFundDao()
    }

}