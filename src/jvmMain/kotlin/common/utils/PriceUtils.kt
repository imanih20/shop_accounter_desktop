package common.utils

object PriceUtils {
    fun calculatePrice(numberOfPack: Int,numberOfItem: Int,totalPrice: Int) : Int {
        val itemNumber = numberOfPack * numberOfItem
        return totalPrice / itemNumber
    }
}