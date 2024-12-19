package com.rifqi.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {

    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())

        val date = inputFormat.parse(inputDate)
        return date?.let { outputFormat.format(it) } ?: "Invalid Date"
    }
}
