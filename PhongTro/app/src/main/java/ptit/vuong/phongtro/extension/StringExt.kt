package ptit.vuong.phongtro.extension

import android.text.Spanned
import androidx.core.text.HtmlCompat
import java.text.SimpleDateFormat
import java.util.Locale


/**
 * Extension function to format the string to a URL
 */
fun String.withThueTroUrl() = "https://thuenhatro360.com/$this"

/**
 * Extension function to format the string to a URL
 */

fun String.timeCreated(): Long {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return try {
        format.parse(this)?.time ?: 0L
    } catch (e: Exception) {
        0L
    }
}

/**
 * Extension function to convert HTML string to Spanned

 */

fun String.toHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

/**
 * Extension function to add square meter to the string
 */

fun String.addSquareMeter() = "$this m²"

/**
 * Extension function to convert the string to Vietnamese currency
 */

fun String.toVietnameseCurrency(): String {
    val value = this.toDoubleOrNull() ?: return "0"
    return (value * 1000000).toLong().toString()
}

/**
 * Extension function to convert the string to passed time
 */

fun String.toPassedTime(): String {
    val time = timeCreated()
    val currentTime = System.currentTimeMillis()
    val passedTime = currentTime - time
    val seconds = passedTime / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val months = days / 30
    val years = months / 12
    return when {
        years > 0 -> "$years năm trước"
        months > 0 -> "$months tháng trước"
        days > 0 -> "$days ngày trước"
        hours > 0 -> "$hours giờ trước"
        minutes > 0 -> "$minutes phút trước"
        else -> "$seconds giây trước"
    }
}