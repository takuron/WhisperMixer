package com.takuron.whisperwavemixer.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SourceCategoryDao {
    @Query("SELECT * FROM source_category")
    fun getAllCategory():List<SourceCategoryData>

    @Insert
    fun insertAll(vararg sourceCategory:SourceCategoryData)

    @Delete
    fun delete(sourceCategory:SourceCategoryData)
}