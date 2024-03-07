package com.hdsw.asimpleapp.ui.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.regex.Pattern


object DateUtils {

    @Throws(ParseException::class)
    fun formatDate(inputDate: String): String {
        val pattern = Pattern.compile("\\w{3} \\w{3} \\d{2} \\d{4} \\d{2}:\\d{2}:\\d{2} GMT[+-]\\d{4}")
        val matcher = pattern.matcher(inputDate)
        if (matcher.find()) {
            val dateStr = matcher.group(0)
            val inputFormat = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.US)
            val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm:ss", Locale.getDefault())
            val date = dateStr?.let { inputFormat.parse(it) }
            date?.let {
                return outputFormat.format(it)
            }
        }
       return ""
    }

}