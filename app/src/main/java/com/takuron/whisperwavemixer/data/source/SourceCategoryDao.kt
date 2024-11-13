package com.takuron.whisperwavemixer.data.source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SourceCategoryDao {
    @Query("SELECT * FROM source_category")
    fun getAllCategoryList():List<SourceCategoryData>
    @Query("SELECT * FROM source_category")
    fun getAllCategoryLiveData():LiveData<List<SourceCategoryData>>
    @Query("SELECT * FROM source_category")
    fun getAllCategoryFlow(): Flow<List<SourceCategoryData>>

    @Insert
    suspend fun insertAll(vararg sourceCategory:SourceCategoryData)

    @Delete
    suspend fun delete(sourceCategory:SourceCategoryData)
}