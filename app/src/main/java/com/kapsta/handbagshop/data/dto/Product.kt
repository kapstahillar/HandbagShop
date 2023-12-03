package com.kapsta.handbagshop.data.dto

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: ProductNameTranslation,
    @SerializedName("price")
    val price: Float // Should be Decimal
)

data class ProductNameTranslation(
    @SerializedName("en")
    val en: String
)