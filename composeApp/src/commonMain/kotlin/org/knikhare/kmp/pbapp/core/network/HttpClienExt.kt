package org.knikhare.kmp.pbapp.core.network

import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.util.network.UnresolvedAddressException
import org.knikhare.kmp.pbapp.core.domain.DataError
import org.knikhare.kmp.pbapp.core.domain.Result
import org.knikhare.kmp.pbapp.core.domain.Error

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, DataError.Remote> {

    val response = try {
        execute()
    } catch(e: SocketTimeoutException) {
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch(e: UnresolvedAddressException) {
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.Remote.Unknown)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DataError.Remote> {
    return try {
        when (response.status.value) {
            in 200..299 -> {
                val data: T = response.body<T>()
                Result.Success(data)
            }
            408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
            429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(DataError.Remote.SERVER_ERROR)
            else -> Result.Error(DataError.Remote.Unknown)
        }
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        Result.Error(DataError.Remote.SERIALIZATION_ERROR)
    }
}