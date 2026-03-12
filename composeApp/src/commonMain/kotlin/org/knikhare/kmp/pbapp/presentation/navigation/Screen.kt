package org.knikhare.kmp.pbapp.presentation.navigation

import org.knikhare.kmp.pbapp.domain.model.Product

sealed class Screen {

    object ProductList : Screen()

    data class ProductDetail(
        val product: Product
    ) : Screen()
}