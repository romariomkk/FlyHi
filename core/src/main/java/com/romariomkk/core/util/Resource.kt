package com.romariomkk.core.util

class Resource<out T> private constructor(
    val status: Status,
    val data: T?,
    val exception: Throwable?) {

    fun isError() = status is Status.Error

    sealed class Status {
        object Success: Status()
        object Loading: Status()
        object Error: Status()
    }

    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.Success, data, null)
        }

        fun <T> error(exception: Throwable? = null, data: T? = null): Resource<T> {
            return Resource(Status.Error, data, exception)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.Loading, data, null)
        }

        fun <T> copy(status: Status, data: T? = null, exception: Throwable? = null): Resource<T> {
            return Resource(status, data, exception)
        }

    }
}

fun <F, T> Resource<F>.copyWith(newData: T?) =
    Resource.copy(status = status, data = newData, exception = exception)

fun <F, T> Resource<F>.mapData(mapper: (F) -> T): Resource<T> {
    return Resource.copy(status = status, data = data?.let { mapper(it) }, exception = exception)
}