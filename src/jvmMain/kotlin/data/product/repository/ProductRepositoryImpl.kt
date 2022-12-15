package data.product.repository

import data.product.model.ProductEntity
import domain.product.model.Product
import domain.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class ProductRepositoryImpl : ProductRepository {
    override suspend fun saveProduct(product: Product) {
        getProductId(product.title).collect {
            println(it)
            if (it > 0) updateProduct(product)
            else addProduct(product)
        }
    }

    private fun updateProduct(product: Product) {
        transaction {
            ProductEntity.update({ ProductEntity.title eq product.title }) {
                with(SqlExpressionBuilder) {
                    it[title] = product.title
                    it[quantity] = quantity + product.quantity
                    it[purchasePrice] = product.purchasePrice
                    it[salePrice] = product.salePrice
                    it[owner] = product.owner
                }
            }
        }
    }

    override suspend fun addProduct(product: Product) {
        transaction {
            ProductEntity.insert {
                it[title] = product.title
                it[quantity] = product.quantity
                it[purchasePrice] = product.purchasePrice
                it[salePrice] = product.salePrice
                it[owner] = product.owner
            }
        }
    }


    suspend fun getProductId(title: String): Flow<Int> {
        return flow {
            var id = 0
            transaction {
                ProductEntity.select { ProductEntity.title eq title }.forEach {
                    id = it[ProductEntity.id].value
                }
            }
            emit(id)
        }
    }

    override suspend fun getALLProducts(): Flow<List<Product>> {
        return flow {
            val list = arrayListOf<Product>()
            transaction {
                list.addAll(Product.getProductListFromQuery(ProductEntity.selectAll()))
            }
            emit(list)
        }
    }

    override suspend fun editProduct(product: Product) {
        transaction {
            ProductEntity.update({
                ProductEntity.id eq product.id
            }) {
                it[title] = product.title
                it[quantity] = product.quantity
                it[purchasePrice] = product.purchasePrice
                it[salePrice] = product.salePrice
                it[owner] = product.owner
            }
        }
    }

    override suspend fun deleteProduct(productId: Int) {
        transaction {
            ProductEntity.deleteWhere {
                ProductEntity.id eq productId
            }
        }
    }

    override suspend fun reduceProductQuantity(name: String, quantity: Double) {
        transaction {
            ProductEntity.update({ ProductEntity.title eq name }) {
                with(SqlExpressionBuilder) {
                    it[ProductEntity.quantity] = ProductEntity.quantity - quantity
                }
            }
        }
    }

}