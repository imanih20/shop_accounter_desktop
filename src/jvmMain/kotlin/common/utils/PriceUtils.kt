package common.utils

object PriceUtils {
    fun calculatePrice(numberOfPack: Int,numberOfItem: Double,totalPrice: Int) : Int {
        val itemNumber = numberOfPack * numberOfItem
        return (totalPrice / itemNumber).toInt()
    }
}