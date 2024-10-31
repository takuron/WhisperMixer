package com.takuron.whisperwavemixer.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SourceFileDao {
    @Query("SELECT * FROM source_file")
    fun getAllSource():List<SourceFileData>
    @Query("SELECT * FROM source_file WHERE category_id IS :category_id")
    fun getSourceByCategory(category_id:Int):List<SourceFileData>

    @Insert
    fun insertAll(vararg source_files:SourceFileData)

    @Delete
    fun delete(source_files:SourceFileData)
}