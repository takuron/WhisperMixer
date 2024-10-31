package com.takuron.whisperwavemixer.data.source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "source_file")
data class SourceFileData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "category_id") var categoryId: Int,

    @ColumnInfo(name = "file_path") var file_path: String,
    @ColumnInfo(name = "file_size") var file_size: Long,
)
