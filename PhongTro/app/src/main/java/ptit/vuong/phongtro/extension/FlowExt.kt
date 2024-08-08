package ptit.vuong.phongtro.extension

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.launch

/**
 * Projects each source value to a [Flow] which is merged in the output [Flow] only if the previous projected [Flow] has completed.
 * If value is received while there is some projected [Flow] sequence being merged it will simply be ignored.
 *
 * This method is a shortcut for `map(transform).flattenFirst()`. See [flattenFirst].
 *
 * ### Operator fusion
 *
 * Applications of [flowOn], [buffer], and [produceIn] _after_ this operator are fused with
 * its concurrent merging so that only one properly configured channel is used for execution of merging logic.
 *
 * @param transform A transform function to apply to value that was observed while no Flow is executing in parallel.
 */
@ExperimentalCoroutinesApi
fun <T, R> Flow<T>.flatMapFirst(transform: suspend (value: T) -> Flow<R>): Flow<R> = map(transform).flattenFirst()

/**
 * Converts a higher-order [Flow] into a first-order [Flow] by dropping inner [Flow] while the previous inner [Flow] has not yet completed.
 */
@ExperimentalCoroutinesApi
fun <T> Flow<Flow<T>>.flattenFirst(): Flow<T> = channelFlow {
  val outerScope = this
  val busy = AtomicBoolean(false)

  collect { inner ->
    if (busy.compareAndSet(false, true)) {
      // Do not pay for dispatch here, it's never necessary
      launch(start = CoroutineStart.UNDISPATCHED) {
        try {
          inner.collect { outerScope.send(it) }
          busy.set(false)
        } catch (e: CancellationException) {
          // cancel outer scope on cancellation exception, too
          outerScope.cancel(e)
        }
      }
    }
  }
}

/**
 * Creates a _cold_ flow that produces a single value from the given [function].
 *
 * Example of usage:
 *
 * ```
 * suspend fun remoteCall(): R = ...
 * fun remoteCallFlow(): Flow<R> = flowFromSuspend(::remoteCall)
 * ```
 */
fun <T> flowFromSuspend(function: suspend () -> T): Flow<T> = flow { emit(function()) }

/**
 * Maps values in the flow to successful results and catches and wraps any exception into a failure result.
 */
fun <T> Flow<T>.asResultFlow(): Flow<Result<T>> = map { kotlin.Result.success(it) }
  .catch { emit(kotlin.Result.failure(it)) }
