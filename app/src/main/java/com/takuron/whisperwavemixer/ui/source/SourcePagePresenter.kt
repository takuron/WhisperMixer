package com.takuron.whisperwavemixer.ui.source

import android.net.Uri
import com.takuron.whisperwavemixer.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.provider.MediaStore
import com.takuron.whisperwavemixer.data.source.SourceCategoryData
import com.takuron.whisperwavemixer.data.source.SourceFileData
import com.takuron.whisperwavemixer.sourceCategoryDao
import com.takuron.whisperwavemixer.sourceFileDao
import com.takuron.whisperwavemixer.utils.LogUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID

val AUDIO_INFO = arrayOf(
    MediaStore.Audio.Media.DISPLAY_NAME,
    MediaStore.Audio.Media.SIZE
)

class SourcePresenter {
    fun saveSource(scope: CoroutineScope, uri: Uri) = scope.launch(Dispatchers.IO) {
        application.contentResolver.query(uri, AUDIO_INFO , null, null, null, null)?.apply {
            if (! moveToFirst()) return@apply

            val fileType = getString(getColumnIndexOrThrow(AUDIO_INFO[0])).split('.').last()
            var fileName = "${UUID.randomUUID()}.$fileType"
            while(File(application.filesDir,fileName).exists())
                fileName = "${UUID.randomUUID()}.$fileType"

            val file = File(application.filesDir,fileName)
            val data = SourceFileData(
                id = null,
                title = getString(getColumnIndexOrThrow(AUDIO_INFO[0])),
                categoryId = 0,
                file_path = file.path,
                file_size = getString(getColumnIndexOrThrow(AUDIO_INFO[1])).toLong(),
            )

            try {
                application.contentResolver.openInputStream(uri).use { inputStream ->
                    FileOutputStream(file).use { outputStream ->
                        val buffer = ByteArray(1024)
                        var length: Int
                        while (inputStream!!.read(buffer).also { length = it } > 0) {
                            outputStream.write(buffer, 0, length)
                        }
                    }

                    sourceFileDao.insertAll(data)
                }
            } catch (e: IOException) {
                LogUtils.logE("write","error ${e.toString()}")
            }
        }?.close()
    }

    fun newCategory(scope: CoroutineScope, name:String) = scope.launch(Dispatchers.IO){
        sourceCategoryDao.insertAll(SourceCategoryData(null,name,0))
    }
}