package ptit.vuong.phongtro.extension

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

fun Fragment.isKeyboardVisible(): Boolean = view?.isKeyboardVisible() ?: false

fun Fragment.waitKeyboardHide(onHidden: () -> Unit) {
  view
    ?.let { view ->
      when (isKeyboardVisible()) {
        true -> {
          view.hideKeyboard()
          view.post { onHidden.invoke() }
        }

        false -> onHidden.invoke()
      }
    }
    ?: run {
      onHidden.invoke()
    }
}

fun ViewPager2.setupWithFragments(fragment: Fragment, fragments: List<() -> Fragment>) {
  adapter = object : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position].invoke()
  }
}

fun Fragment.setBackWithResult(key: String, bundle: Bundle) {
  setFragmentResult(key, bundle)
  findNavController().navigateUp()
}

inline fun <reified T : Parcelable> Fragment.getParcelableCompat(key: String): T? = if (Build.VERSION.SDK_INT >=
  Build.VERSION_CODES.TIRAMISU
) {
  requireArguments().getParcelable(key, T::class.java)
} else {
  requireArguments().getParcelable(key)
}
