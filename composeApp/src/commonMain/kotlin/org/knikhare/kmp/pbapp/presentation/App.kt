package org.knikhare.kmp.pbapp.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import io.ktor.client.HttpClient
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

        Scaffold(
            topBar = {
                @OptIn(ExperimentalMaterial3Api::class)
                TopAppBar(
                    title = {
                        Text(
                            text = when (currentScreen) {
                                is Screen.ProductList -> "Products"
                                is Screen.ProductDetail -> "Product Details"
                            }
                        )
                    },
                    navigationIcon = {
                        if (currentScreen is Screen.ProductDetail) {
                            IconButton (
                                onClick = {
                                    currentScreen = Screen.ProductList
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,  // Background color
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,  // Text color
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary  // Icon color
                    )

                )
            }
        ) { paddingValues ->
            Box(
                modifier = androidx.compose.ui.Modifier.padding(paddingValues)
            ) {
                when (val screen = currentScreen) {
                    is Screen.ProductList -> {
                        ProductListScreen(
                            viewModel = viewModel,
                            onProductClick = { product ->
                                currentScreen = Screen.ProductDetail(product)
                            }
                        )
                    }
                    is Screen.ProductDetail -> {
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

    }
}