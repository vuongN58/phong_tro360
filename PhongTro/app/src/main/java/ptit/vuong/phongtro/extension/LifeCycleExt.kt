package ptit.vuong.phongtro.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

/**
 * Launches a new coroutine and repeats `block` every time the Fragment's viewLifecycleOwner
 * is in and out of `minActiveState` lifecycle state.
 * @param launchBlocks The blocks to launch in the coroutine.
 * @param doAfterLaunch The block to execute after launching the `launchBlocks`.
 * If `null`, nothing will be executed after launching the `launchBlocks`.
 * If not `null`, it will be executed after launching the `launchBlocks`.
 * This is useful for executing some code after launching the `launchBlocks`.
 * For example, you can use this to show a progress bar after launching the `launchBlocks`.
 * @see repeatOnLifecycle
 * @see launch
 * @see viewLifecycleOwner
 * @see Lifecycle.State.STARTED
 * @see viewLifecycleOwner.lifecycleScope
 * @see viewLifecycleOwner.repeatOnLifecycle
 */

fun Fragment.launchAndRepeatStarted(vararg launchBlocks: suspend () -> Unit, doAfterLaunch: (() -> Unit)? = null) {
  viewLifecycleOwner.lifecycleScope.launch {
    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
      launchBlocks.forEach { launch { it() } }
      doAfterLaunch?.invoke()
    }
  }
}
