package org.knikhare.kmp.pbapp.domain.api

import org.knikhare.kmp.pbapp.core.domain.DataError
import org.knikhare.kmp.pbapp.data.remote.dto.ProductDto
import org.knikhare.kmp.pbapp.data.remote.dto.ProductResponse
import org.knikhare.kmp.pbapp.domain.model.Product
import org.knikhare.kmp.pbapp.core.domain.Result

interface ProductRepository {

    suspend fun getProducts(): Result<ProductResponse, DataError.Remote>

    suspend fun searchProducts(query: String): Result<ProductResponse, DataError.Remote>

    suspend fun getProductsByCategory(category: String): Result<ProductResponse, DataError.Remote>
}