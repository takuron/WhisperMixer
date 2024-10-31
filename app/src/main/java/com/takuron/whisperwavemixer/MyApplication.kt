package com.takuron.whisperwavemixer

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.takuron.whisperwavemixer.data.source.SourceCategoryData
import com.takuron.whisperwavemixer.data.source.SourceFileDao
import com.takuron.whisperwavemixer.data.source.SourceFileData

lateinit var application: MyApplication

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }
}

