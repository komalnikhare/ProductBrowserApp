package org.knikhare.kmp.pbapp.domain.usecases

import org.knikhare.kmp.pbapp.core.domain.Result
import org.knikhare.kmp.pbapp.data.remote.dto.ProductDto
import org.knikhare.kmp.pbapp.domain.usecase.GetProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

import org.knikhare.kmp.pbapp.core.domain.DataError
import org.knikhare.kmp.pbapp.data.mapper.toDomain
import org.knikhare.kmp.pbapp.data.remote.dto.ProductResponse
import org.knikhare.kmp.pbapp.domain.api.ProductRepository
import org.knikhare.kmp.pbapp.domain.model.Product
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetProductsUseCaseTest {

    private val repository = mockk<ProductRepository>()
    private val useCase = GetProductsUseCase(repository)

    @Test
    fun `invoke returns success with mapped products when repository succeeds`() = runTest {
        // Given
        val dto = mockProduct
        val response = ProductResponse(products = listOf(dto))
        val expectedProducts = listOf(dto.toDomain())
        coEvery { repository.getProducts() } returns Result.Success(response)

        // When
        val result = useCase()

        // Then
        assertTrue(result is Result.Success)
        assertEquals(expectedProducts, (result as Result.Success).data)
    }

    @Test
    fun `invoke returns error when repository fails`() = runTest {
        // Given
        val error = DataError.Remote.REQUEST_TIMEOUT
        coEvery { repository.getProducts() } returns Result.Error(error)

        // When
        val result = useCase()

        // Then
        assertTrue(result is Result.Error)
        assertEquals(error, (result as Result.Error).error)
    }

    @Test
    fun `invoke returns empty list when repository returns empty products`() = runTest {
        // Given
        val response = ProductResponse(products = emptyList())
        coEvery { repository.getProducts() } returns Result.Success(response)

        // When
        val result = useCase()

        // Then
        assertTrue(result is Result.Success)
        assertEquals(emptyList<Product>(), (result as Result.Success).data)
    }

  val mockProduct =  ProductDto(
    id = 1,
    title = "iPhone 14",
    description = "Apple smartphone",
    price = 999.0,
    rating = 4.8,
    brand = "Apple",
    thumbnail = "image_url"
    )
}
