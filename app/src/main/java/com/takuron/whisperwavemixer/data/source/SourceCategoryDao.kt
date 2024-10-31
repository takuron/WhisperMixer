package com.takuron.whisperwavemixer.data.source

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SourceCategoryDao {
    @Query("SELECT * FROM source_category")
    fun getAllCategory():List<SourceCategoryData>
}