package org.knikhare.kmp.pbapp.domain.usecase

import org.knikhare.kmp.pbapp.core.domain.DataError
import org.knikhare.kmp.pbapp.core.domain.Result
import org.knikhare.kmp.pbapp.core.domain.map
import org.knikhare.kmp.pbapp.data.mapper.toDomain
import org.knikhare.kmp.pbapp.domain.api.ProductRepository
import org.knikhare.kmp.pbapp.domain.model.Product

class GetProductsUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(): Result<List<Product>, DataError.Remote> {
        return repository.getProducts().map { dto ->
            dto.products.map {
                it.toDomain()
            }
        }
    }
}