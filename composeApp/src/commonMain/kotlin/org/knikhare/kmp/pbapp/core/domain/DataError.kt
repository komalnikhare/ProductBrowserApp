package org.knikhare.kmp.pbapp.core.domain

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION_ERROR,
        Unknown
    }

    enum class Local: DataError {
        DISK_FULL,
        INSUFFICIENT_FUNDS,
        Unknown
    }
}