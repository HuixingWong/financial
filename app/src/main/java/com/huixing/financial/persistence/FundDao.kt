package com.huixing.financial.persistence

import androidx.room.*
import com.huixing.financial.model.BaseFundData

@Dao
interface FundDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFundList(fundList: List<BaseFundData>)

    @Query("SELECT * FROM BaseFundData WHERE name Like '%' || :name || '%' or code Like '%' || :code || '%'")
    suspend fun getSearchFundList(name: String? = null, code: String? = null): List<BaseFundData>

    @Query("SELECT * FROM BaseFundData WHERE code == :fundCode")
    suspend fun getCollectionByCode(fundCode: String?): BaseFundData

    @Query("SELECT * FROM BaseFundData")
    suspend fun getAllFundList(): List<BaseFundData>

    @Query("SELECT COUNT(*) FROM BaseFundData")
    suspend fun getDataCount(): Int

    @Query("SELECT * FROM BaseFundData WHERE collect > 0")
    suspend fun getCollectionFund(): List<BaseFundData>

    @Update
    suspend fun update(vararg baseFundData: BaseFundData)

}
