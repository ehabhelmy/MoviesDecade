package com.twi.moviesdecade.utils

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val calendar = Calendar.getInstance()

    internal enum class WeekDays constructor(var value: String) {
        SATURDAY("Saturday"),
        SUNDAY("Sunday"),
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday")
    }
    fun getFormatedTime(time: String): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.US)
        val outputDateFormat = SimpleDateFormat("yyyy-mm-dd", Locale.US)
        var outString: String? = null
        try {
            val date = dateFormat.parse(time)
            outString = outputDateFormat.format(date)
        } catch (e: ParseException) {
            Timber.i(e.message)
        }
        return outString
    }

    fun calcDifferenceInDate(dateAsString : String?) : String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = simpleDateFormat.parse(dateAsString)
        val diffInDays = (System.currentTimeMillis() - date.time) / (1000 * 60 * 60 * 24)
        return when {
            diffInDays <= 1 -> "TODAY"
            diffInDays <= 2 -> "YESTERDAY"
            else -> "$diffInDays Days Ago"
        }
    }
}
