package org.knikhare.kmp.pbapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.knikhare.kmp.pbapp.data.remote.dto.ProductResponse


private const val BASE_URL = "https://dummyjson.com/products"

class ProductApi(private val client: HttpClient) {

    suspend fun getProducts(): ProductResponse {
        return client.get (BASE_URL).body()
    }

    suspend fun searchProducts(query: String): ProductResponse {
        return client.get("$BASE_URL/search?q=$query").body()
    }

    suspend fun getProductsByCategory(category: String): ProductResponse {
        return client.get("$BASE_URL/category/$category").body()
    }
}