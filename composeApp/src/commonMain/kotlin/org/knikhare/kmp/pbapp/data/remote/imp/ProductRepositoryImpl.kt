package org.knikhare.kmp.pbapp.data.remote.imp

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.knikhare.kmp.pbapp.core.domain.DataError
import org.knikhare.kmp.pbapp.core.domain.Result
import org.knikhare.kmp.pbapp.core.network.safeCall
import org.knikhare.kmp.pbapp.data.remote.dto.ProductResponse
import org.knikhare.kmp.pbapp.domain.api.ProductRepository


private const val BASE_URL = "https://dummyjson.com/products"
class ProductRepositoryImpl(
    private val client: HttpClient
): ProductRepository {
    override suspend fun getProducts(): Result<ProductResponse, DataError.Remote> {
        return safeCall {
            client.get(BASE_URL )

        }
    }

    override suspend fun searchProducts(query: String): Result<ProductResponse, DataError.Remote> {

        return safeCall {
            client.get ( "${BASE_URL}/search?q=$query" )
        }
    }

    override suspend fun getProductsByCategory(category: String): Result<ProductResponse, DataError.Remote> {

        return safeCall {
            client.get( "${BASE_URL}/category/$category" )
        }
    }

}