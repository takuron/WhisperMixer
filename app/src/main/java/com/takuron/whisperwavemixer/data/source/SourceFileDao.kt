package com.takuron.whisperwavemixer.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SourceFileDao {
    @Query("SELECT * FROM source_file")
    fun getAllSource():List<SourceFileData>
    @Query("SELECT * FROM source_file WHERE category_id IS :categoryId")
    fun getSourceByCategory(categoryId:Int):List<SourceFileData>

    @Insert
    fun insertAll(vararg sourceFiles:SourceFileData)

    @Delete
    fun delete(sourceFiles:SourceFileData)
}