package com.takuron.whisperwavemixer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.takuron.whisperwavemixer.data.source.SourceCategoryData
import com.takuron.whisperwavemixer.data.source.SourceFileData
import com.takuron.whisperwavemixer.sourceCategoryDao
import com.takuron.whisperwavemixer.sourceFileDao

class SourcePageViewModel : ViewModel() {
    val presenter = SourcePresenter()

    val categoryListLiveData:LiveData<List<SourceCategoryData>> = sourceCategoryDao.getAllCategoryLiveData()
    val fileListLiveData:LiveData<List<SourceFileData>> = sourceFileDao.getSourceByCategoryLiveData(0)
}