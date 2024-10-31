package com.takuron.whisperwavemixer

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.takuron.whisperwavemixer.data.source.SourceCategoryData
import com.takuron.whisperwavemixer.data.source.SourceFileDao
import com.takuron.whisperwavemixer.data.source.SourceFileData

@Database(entities = [SourceFileData::class, SourceCategoryData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sourceFileDao(): SourceFileDao
}

val appDataBase by lazy {
    Room.databaseBuilder(
        application,
        AppDatabase::class.java, "app_database"
    ).build()
}
val sourceFileDao by lazy { appDataBase.sourceFileDao() }