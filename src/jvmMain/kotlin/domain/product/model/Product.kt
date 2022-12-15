package domain.product.model

import data.product.model.ProductEntity
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import java.io.Serializable

data class Product(
    val id: Int = 0,
    val title: String,
    val quantity: Double,
    val purchasePrice: Int,
    val salePrice: Int,
    val owner: String,
) : Serializable {
    companion object {
        fun getProductFromResult(result: ResultRow): Product {
            return Product(
                result[ProductEntity.id].value,
                result[ProductEntity.title],
                result[ProductEntity.quantity],
                result[ProductEntity.purchasePrice],
                result[ProductEntity.salePrice],
                result[ProductEntity.owner],
            )
        }

        fun getProductListFromQuery(query: Query): List<Product> {
            val list = arrayListOf<Product>()
            query.forEach {
                list.add(
                    getProductFromResult(it)
                )
            }
            return list
        }
    }

    override fun toString(): String {
        return title
    }
}
