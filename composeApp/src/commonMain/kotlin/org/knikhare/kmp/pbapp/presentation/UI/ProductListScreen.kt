package org.knikhare.kmp.pbapp.presentation.UI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.knikhare.kmp.pbapp.domain.model.Product
import org.knikhare.kmp.pbapp.presentation.component.CategoryFilter
import org.knikhare.kmp.pbapp.presentation.component.ProductItem
import org.knikhare.kmp.pbapp.presentation.viewmodel.ProductViewModel

@Composable
        /**
         * Composable function that displays a list of products with a search bar.
         * @param viewModel The view model for managing product state.
         * @param onProductClick Callback when a product is clicked.
         */
fun ProductListScreen(
    viewModel: ProductViewModel,
    onProductClick: (product: Product) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit){
        viewModel.loadProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.search(it)
            },
            label = { Text("Search Country") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        CategoryFilter(
            onCategorySelected = { category ->
                viewModel.filterByCategory(category)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(state.products) { product ->
                ProductItem(product = product){
                    onProductClick(product)
                }
            }
        }
    }
}