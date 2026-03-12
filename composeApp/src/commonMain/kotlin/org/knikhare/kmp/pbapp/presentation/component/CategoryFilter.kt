package org.knikhare.kmp.pbapp.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

val categories = listOf(
    "smartphones",
    "laptops",
    "fragrances",
    "skincare",
    "groceries",
    "home-decoration"
)

@Composable
fun CategoryFilter(
    onCategorySelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("Filter Category") }

    Box {

        Button(onClick = { expanded = true }) {
            Text(selectedCategory)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            categories.forEach { category ->

                DropdownMenuItem(
                    text = { Text(category) },
                    onClick = {
                        selectedCategory = category
                        expanded = false

                        onCategorySelected(category)
                    }
                )
            }
        }
    }
}