package com.test.app.newsapp.data.source.remote.bbc.parser

import android.os.Build
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class DateFormatParser {

    companion object {

        fun parse(inputDate: String): String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ISO_INSTANT.withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.RFC_1123_DATE_TIME.parse(inputDate))
                .toString()
        } else {
            try {
                val inputFormat =
                    SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.getDefault())
                val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                outputFormat.format(inputFormat.parse(inputDate))
            } catch (e: ParseException) {
                inputDate
            }
        }

    }
}