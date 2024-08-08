package ptit.vuong.phongtro.extension

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import java.util.Locale

/**
 * Show toast message
 */

fun Context.toaster(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Open browser with url
 */

fun Context.navigateToUrl(url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
    }
}

/**
 * Call to owner phone number
 */

fun Context.callToOwner(phone: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phone")
    startActivity(intent)
}

/**
 * Share room to other apps
 */

fun Context.shareRoom(room: String) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, room)
    startActivity(Intent.createChooser(shareIntent, null))
}

/**
 * Send SMS to phone number
 */

fun Context.sendSms(phone: String) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("smsto:$phone")
    startActivity(intent)
}

/**
 * Open map with latitude and longitude
 */

fun Context.openMap(lat: Double, lng: Double) {
    val uri = String.format(Locale.getDefault(), "geo:%f,%f", lat, lng)
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    startActivity(intent)
}


/**
 * Turn on GPS
 */

fun Context.turnOnGps() {
    val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
    startActivity(intent)
}