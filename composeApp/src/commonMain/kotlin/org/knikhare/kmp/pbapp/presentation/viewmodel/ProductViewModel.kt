package org.knikhare.kmp.pbapp.presentation.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.knikhare.kmp.pbapp.core.domain.DataError
import org.knikhare.kmp.pbapp.core.domain.Result
import org.knikhare.kmp.pbapp.domain.model.Product
import org.knikhare.kmp.pbapp.domain.usecase.GetProductsByCategoryUseCase
import org.knikhare.kmp.pbapp.domain.usecase.GetProductsUseCase
import org.knikhare.kmp.pbapp.domain.usecase.SearchProductsUseCase
import org.knikhare.kmp.pbapp.presentation.ProductUiState

class ProductViewModel(
    private val getProducts: GetProductsUseCase,
    private val searchProducts: SearchProductsUseCase,
    private val getProductsByCategory: GetProductsByCategoryUseCase) {

    private val _state = MutableStateFlow(ProductUiState())
    val state = _state.asStateFlow()

    fun loadProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            updateResponse(getProducts())
        }
    }

    fun search(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            updateResponse(searchProducts(query))
        }
    }

    fun filterByCategory(category: String) {
        CoroutineScope(Dispatchers.Main).launch {
            updateResponse(getProductsByCategory(category))
        }
    }

    private fun updateResponse( response: Result<List<Product>, DataError.Remote>){
        when(response){
            is Result.Success ->{
                _state.update {
                    it.copy(products = response.data)
                }
            }
            is Result.Error ->{
                _state.update {
                    it.copy(
                        products = emptyList(),
                        error = response.error.toString()
                    )
                }
            }
        }
    }

}