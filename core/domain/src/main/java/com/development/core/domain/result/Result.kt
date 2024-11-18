package com.development.core.domain.result

sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E: com.development.core.domain.result.Error>(val error: E) :
        Result<Nothing, E>
}

inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

typealias EmptyDataResult<E> = Result<Unit, E>

fun <T: Any, E: Error> Result<T, E>.onSuccess(
    executable: (T) -> Unit
): Result<T, E> = apply {
    if (this is Result.Success<T>) {
        executable(data)
    }
}

fun <T: Any, E: Error> Result<T, E>.onError(
    executable: (error: E) -> Unit
): Result<T, E> = apply {
    if (this is Result.Error) {
        executable(this.error)
    }
}