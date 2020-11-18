package com.huixing.financial.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.huixing.financial.model.BaseFundData

@Dao
interface FundDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFundList(fundList: List<BaseFundData>)

    @Query("SELECT * FROM BaseFundData WHERE name Like '%' || :name || '%' or code Like '%' || :code || '%'")
    suspend fun getSearchFundList(name: String? = null, code: String? = null): List<BaseFundData>

    @Query("SELECT * FROM BaseFundData")
    suspend fun getAllFundList(): List<BaseFundData>
}
