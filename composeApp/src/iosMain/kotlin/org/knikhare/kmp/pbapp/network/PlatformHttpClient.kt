package org.knikhare.kmp.pbapp.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import org.knikhare.kmp.pbapp.core.network.HttpClientFactory

actual fun createHttpClient(): HttpClient {
    return HttpClientFactory.create(Darwin.create())
}