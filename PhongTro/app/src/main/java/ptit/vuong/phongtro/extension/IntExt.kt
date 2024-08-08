package ptit.vuong.phongtro.extension

/**
 * Convert an integer to Vietnam Dong format
 * @return the formatted string
 */

fun Int.convertToVietnamDong(): String {
    val million = this / 1000000
    val thousand = (this % 1000000) / 1000
    val thousandFormatted = if (thousand > 0) thousand.toString().trimEnd('0') else ""

    return if (million > 0) {
        if (thousandFormatted.isNotEmpty()) {
            "$million.$thousandFormatted triệu"
        } else {
            "$million triệu"
        }
    } else {
        "$thousand nghìn"
    }
}
