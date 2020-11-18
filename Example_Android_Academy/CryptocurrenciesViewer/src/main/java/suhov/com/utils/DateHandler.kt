package suhov.com.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateHandler(dateParse: String) {
    private val OLD_DATE_FORMAT = "MMM dd, yyyy h:mm:ss a"
    private val NEW_DATE_FORMAT = "d MMM yyyy HH:mm:ss"
    private var newLine = ""
    val completedLine: String
    private val dateNow = Date(System.currentTimeMillis())
    private var date = Date()
    private var days: Long = 0
    private var hours: Long = 0
    private var minutes: Long = 0
    private var seconds: Long = 0
    private var months: Long = 0
    private var years: Long = 0
    private val fitTextOnDisplay = false
    private val orientationLandscape = false
    private fun getNewDataFormat(dateParse: String): String {
        newLine = if (!fitTextOnDisplay) "\n" else ""
        val old_sdf = SimpleDateFormat(OLD_DATE_FORMAT, Locale.ENGLISH)
        val new_sdf = SimpleDateFormat(NEW_DATE_FORMAT)
        try {
            date = old_sdf.parse(dateParse)
            val raw = dateNow.time - date.time
            seconds = raw / 1000
            minutes = seconds / 60
            hours = minutes / 60
            days = hours / 24
            months = days / 30
            years = months / 12
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (years > 0) {
            return years.toString() + " лет назад. " + newLine + new_sdf.format(date)
        } else if (months > 0) {
            return months.toString() + " месяца назад. " + newLine + new_sdf.format(date)
        } else if (days > 0) {
            return days.toString() + " дней назад. " + newLine + new_sdf.format(date)
        } else if (hours > 0) {
            return hours.toString() + " часов назад. " + newLine + new_sdf.format(date)
        } else if (minutes > 0) {
            return minutes.toString() + " минут назад. " + newLine + new_sdf.format(date)
        } else if (seconds > 0) {
            return seconds.toString() + " секунд назад. " + newLine + new_sdf.format(date)
        }
        return "Только что."
    }

    init {
        completedLine = getNewDataFormat(dateParse)
    }
}