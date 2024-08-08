package ptit.vuong.phongtro.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<S : Any> :
  ViewModel(),
  StoreObservable<S> {

  private val store by lazy {
    ViewStateStore(this.initState())
  }

  val currentState: S
    get() = store.uiState.value

  abstract fun initState(): S

  override fun <T> observe(
    owner: LifecycleOwner,
    lifecycleScope: LifecycleCoroutineScope,
    lifecycleState: Lifecycle.State,
    selector: (S) -> T,
    observer: OnChangedObserver<T>,
  ) {
    store.observe(owner, lifecycleScope, lifecycleState, selector, observer)
  }

  protected fun dispatchState(state: S) {
    store.dispatchState(state = state)
  }
}
