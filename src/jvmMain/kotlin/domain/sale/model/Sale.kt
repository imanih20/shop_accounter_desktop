package domain.sale.model

data class Sale(
    val pid: String,
    val quantity: Int,
    val totalPrice: Int,
    val date: String
)