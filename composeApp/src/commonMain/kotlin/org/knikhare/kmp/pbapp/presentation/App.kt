package org.knikhare.kmp.pbapp.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.knikhare.kmp.pbapp.core.network.createHttpClient

import org.knikhare.kmp.pbapp.data.remote.imp.ProductRepositoryImpl
import org.knikhare.kmp.pbapp.domain.usecase.GetProductsByCategoryUseCase
import org.knikhare.kmp.pbapp.domain.usecase.GetProductsUseCase
import org.knikhare.kmp.pbapp.domain.usecase.SearchProductsUseCase
import org.knikhare.kmp.pbapp.presentation.UI.ProductDetailScreen
import org.knikhare.kmp.pbapp.presentation.UI.ProductListScreen
import org.knikhare.kmp.pbapp.presentation.navigation.Screen
import org.knikhare.kmp.pbapp.presentation.viewmodel.ProductViewModel

@Composable
@Preview
fun App() {
    val httpClient = remember { createHttpClient() }

    val repository = remember { ProductRepositoryImpl(httpClient) }
    val getProductsUseCase = remember { GetProductsUseCase(repository) }
    val searchProductsUseCase = remember { SearchProductsUseCase(repository) }
    val getProductsByCategoryUseCase = remember { GetProductsByCategoryUseCase(repository) }

    val viewModel = remember {
        ProductViewModel(
            getProducts = getProductsUseCase,
            searchProducts = searchProductsUseCase,
            getProductsByCategory = getProductsByCategoryUseCase
        )
    }
    var currentScreen by remember {
        mutableStateOf<Screen>(Screen.ProductList)
    }

    MaterialTheme {
        when(val screen = currentScreen){
            is Screen.ProductList -> {
                ProductListScreen(
                    viewModel = viewModel,
                    onProductClick = { product ->
                        currentScreen = Screen.ProductDetail(product)
                    }
                )
            }
            is Screen.ProductDetail-> {
                ProductDetailScreen(
                    product = screen.product,
                    onBack = {
                        currentScreen = Screen.ProductList
                    }
                )
            }
        }
    }
}