package ptit.vuong.phongtro.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

  data class Success<out T>(val data: T) : Result<T>()
  data class Error<out T>(val errorMessage: ErrorMessage? = null) : Result<T>()
  object Loading : Result<Nothing>()

  override fun toString(): String = when (this) {
    is Success<*> -> "Success[data=$data]"
    is Error -> "Error[errorMessage=$errorMessage]"
    Loading -> "Loading"
  }
}

/**
 * ErrorMessage wrapper
 */
data class ErrorMessage(val errorCode: String = "", val exception: Exception? = null)

/**
 * `true` if [Result] is of type [Result.Success] & holds non-null [Result.Success.data].
 */
val Result<*>.succeeded
  get() = this is Result.Success && data != null

/**
 * `true` if [Result] is of type [Result.Error].
 */
val Result<*>.failed
  get() = this is Result.Error

fun <T> Result<T>.successOr(fallback: T): T = (this as? Result.Success<T>)?.data ?: fallback

val <T> Result<T>.data: T?
  get() = (this as? Result.Success)?.data

val <T> Result<T>.requireData: T
  get() = (this as Result.Success).data

val <T> Result<T>.error: Exception?
  get() = (this as? Result.Error)?.errorMessage?.exception

val <T> Result<T>.errorMessage: ErrorMessage?
  get() = (this as? Result.Error)?.errorMessage
