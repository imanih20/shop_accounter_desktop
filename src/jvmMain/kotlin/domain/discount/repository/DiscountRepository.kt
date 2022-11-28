package domain.discount.repository

import domain.discount.model.Discount

interface DiscountRepository {
    suspend fun addDiscount(discount: Discount)

    suspend fun getDiscount(pid: String)
}