package domain.product.model

import java.io.Serializable

data class Product(
    val id : String = "",
    val title: String,
    val quantity: Double,
    val purchasePrice: Int,
    val salePrice: Int,
    val owner: String,
    val pDate: String  = ""
) : Serializable{
    override fun toString(): String {
        return title
    }
}
