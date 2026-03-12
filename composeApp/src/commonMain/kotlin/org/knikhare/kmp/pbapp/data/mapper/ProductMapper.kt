package org.knikhare.kmp.pbapp.data.mapper

import org.knikhare.kmp.pbapp.data.remote.dto.ProductDto
import org.knikhare.kmp.pbapp.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        brand = brand,
        price = price,
        rating = rating,
        thumbnail = thumbnail
    )
}