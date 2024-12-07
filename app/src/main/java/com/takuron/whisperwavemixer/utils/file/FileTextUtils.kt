package com.takuron.whisperwavemixer.utils.file

import java.math.BigDecimal
import java.math.RoundingMode

object FileTextUtils {
    fun size4Display(fileSize:Long,decimal:Int = 2):String = when{
        fileSize<=1000 -> "$fileSize bytes"
        fileSize<=1000*1000 -> "${fileSize/1000} kB"
        fileSize<=1000*1000*1000 -> "${BigDecimal(fileSize).divide(BigDecimal(1000*1000)).setScale(decimal, RoundingMode.FLOOR).toFloat()} MB"
        else -> "${BigDecimal(fileSize).divide(BigDecimal(1000*1000*1000)).setScale(decimal, RoundingMode.FLOOR).toFloat()} GB"
    }
}