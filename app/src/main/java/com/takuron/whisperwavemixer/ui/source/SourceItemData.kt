package com.takuron.whisperwavemixer.ui.source

enum class SourceItemType(){
    TYPE_CATEGORY,
    TYPE_SOURCE
}

data class SourceItemData(
    val type:SourceItemType,
)
