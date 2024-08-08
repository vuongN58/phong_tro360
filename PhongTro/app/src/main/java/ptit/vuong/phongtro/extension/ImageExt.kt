package ptit.vuong.phongtro.extension

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

/**
 * Load image from url with loading with Glide
 * @param url: url of image
 * @return Unit
 **/

fun ImageView.loadImageFromUrlWithLoading(url: String) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide.with(context)
        .load(url)
        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .placeholder(circularProgressDrawable)
        .into(this)
}