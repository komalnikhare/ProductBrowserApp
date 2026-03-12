package org.knikhare.kmp.pbapp.presentation.UI

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil3.compose.AsyncImage
import org.knikhare.kmp.pbapp.domain.model.Product

@Composable
fun ProductDetailScreen(
    product: Product,
    onBack: () -> Unit) {

    Column {

        Button(onClick = onBack) {
            Text("Back")
        }

        AsyncImage(
            model = product.thumbnail,
            contentDescription = null
        )

        Text(product.title)

        Text(product.description)

        Text("Brand: ${product.brand}")

        Text("Price: $${product.price}")

        Text("Rating: ${product.rating}")

    }
}