package org.knikhare.kmp.pbapp.presentation

import org.knikhare.kmp.pbapp.domain.model.Product

data class ProductUiState (
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)