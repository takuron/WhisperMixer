package com.takuron.whisperwavemixer.data.source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.takuron.whisperwavemixer.sourceFileDao

@Entity(tableName = "source_category")
data class SourceCategoryData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "size") var size: Int,
)
