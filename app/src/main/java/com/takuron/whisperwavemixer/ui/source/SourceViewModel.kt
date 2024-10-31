package com.takuron.whisperwavemixer.ui.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SourceViewModel : ViewModel() {
    val presenter = SourcePresenter()
}