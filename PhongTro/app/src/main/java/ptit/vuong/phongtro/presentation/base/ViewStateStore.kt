package ptit.vuong.phongtro.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewStateStore<T : Any>(initialState: T) : StoreObservable<T> {
  // Backing property to avoid state updates from other classes
  private val _uiState = MutableStateFlow(initialState)

  // The UI collects from this StateFlow to get its state updates. Read-only
  val uiState: StateFlow<T> = _uiState.asStateFlow()

  override fun <S> observe(
    owner: LifecycleOwner,
    lifecycleScope: LifecycleCoroutineScope,
    lifecycleState: Lifecycle.State,
    selector: (T) -> S,
    observer: OnChangedObserver<S>,
  ) {
    lifecycleScope.launch {
      owner.repeatOnLifecycle(lifecycleState) {
        uiState.map(selector)
          .distinctUntilChanged()
          .collect { observer(it) }
      }
    }
  }

  fun dispatchState(state: T) {
    _uiState.update { state }
  }
}

interface StoreObservable<T : Any> {
  fun <S> observe(
    owner: LifecycleOwner,
    lifecycleScope: LifecycleCoroutineScope,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    selector: (T) -> S,
    observer: OnChangedObserver<S>,
  )
}

typealias OnChangedObserver<T> = (T) -> Unit
