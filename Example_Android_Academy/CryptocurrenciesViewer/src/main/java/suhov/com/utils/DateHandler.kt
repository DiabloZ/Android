package suhov.com.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.*
import java.util.Date.from

class DateHandler {
    private val NEW_DATE_FORMAT = "HH:mm:ss  d.MM.yyyy"

    @SuppressLint("NewApi", "SimpleDateFormat")
    fun getNewDataFormatFromString(dateParse: String): String {
        val accessor:TemporalAccessor = DateTimeFormatter.ISO_DATE_TIME.parse(dateParse)
        val date: Date = from(Instant.from(accessor))
        return SimpleDateFormat(NEW_DATE_FORMAT).format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun getNewDataFormatFromInt(dateParse: Int): String {
        return SimpleDateFormat(NEW_DATE_FORMAT).format(Date ((dateParse * 1000).toLong()))
    }
}