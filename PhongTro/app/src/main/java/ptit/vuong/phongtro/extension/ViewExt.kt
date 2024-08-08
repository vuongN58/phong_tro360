package ptit.vuong.phongtro.extension

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ptit.vuong.phongtro.utils.SafeOnClickListener

fun View.visible() {
    isVisible = true
}

fun View.gone() {
    isGone = true
}

fun View.invisible() {
    isInvisible = true
}

fun View.visibleIf(condition: Boolean, gone: Boolean = true) = if (condition) {
    visible()
} else {
    if (gone) gone() else invisible()
}

fun View.visibleIf(condition: () -> Boolean) = visibleIf(condition(), true)

fun View.isKeyboardVisible(): Boolean = ViewCompat.getRootWindowInsets(this)
    ?.isVisible(WindowInsetsCompat.Type.ime())
    ?: false

inline val ViewGroup.inflater: LayoutInflater get() = LayoutInflater.from(context)

val RecyclerView.ViewHolder.adapterPositionOrNull: Int?
    inline get() = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }

fun View.onClick(safe: Boolean = true, action: (View) -> Unit) = setOnClickListener(
    SafeOnClickListener(safe, action),
)

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboardAndClearFocus() {
    clearFocus()
    hideKeyboard()
}

fun View.focusAndShowKeyboard() {
    fun View.showTheKeyboardNow() {
        if (isFocused) {
            post {
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }

    requestFocus()
    if (hasWindowFocus()) {
        showTheKeyboardNow()
    } else {
        viewTreeObserver.addOnWindowFocusChangeListener(
            object : ViewTreeObserver.OnWindowFocusChangeListener {
                override fun onWindowFocusChanged(hasFocus: Boolean) {
                    if (hasFocus) {
                        this@focusAndShowKeyboard.showTheKeyboardNow()
                        viewTreeObserver.removeOnWindowFocusChangeListener(this)
                    }
                }
            },
        )
    }
}

fun View.setBackgroundTint(color: Int) {
    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
}

fun ImageView.setTintColor(color: Int) {
    imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
}

fun View.changeColor(propertyName: String, startColor: Int, endColor: Int, duration: Long = 300L) {
    val colorChangeAnimator = ObjectAnimator.ofObject(
        this,
        propertyName,
        android.animation.ArgbEvaluator(),
        startColor,
        endColor,
    )
    colorChangeAnimator.duration = duration
    colorChangeAnimator.setAutoCancel(true)
    colorChangeAnimator.start()
}

fun EditText.setRegexError(regex: Regex, errorText: String) {
    if (!text.toString().matches(regex)) {
        this.error = errorText
    } else {
        error = null
    }
}
