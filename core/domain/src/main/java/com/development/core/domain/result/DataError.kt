package com.development.core.domain.result

interface Error

sealed interface DataError : Error {

    enum class Local : DataError {
        DISK_FULL, NOT_FOUND
    }

}