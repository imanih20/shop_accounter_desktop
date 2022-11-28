package peresentation.base.components.main.components.addProduct

import common.utils.Controller
import domain.financier.model.Financier
import domain.product.model.Product

class AddProductController : Controller {
    fun addProduct(title: String,totalNumber: Int,purchasePrice: Double, salePrice: Double,owner: String){
    }

    fun getAllProducts() : List<Product> {
        return arrayListOf(
            Product("","item 1",10.0,1000,1200,"me"),
            Product("","item 2",10.0,1000,1200,"me"),
            Product("","item 3",10.0,1000,12000,"me"),
            Product("","item 4",10.0,1000,12000,"me"),
            Product("","item 1",10.0,1000,12000,"me"),
            Product("","item 2",10.0,1000,12000,"me"),
            Product("","item 3",10.0,1000,12000,"me"),
            Product("","item 4",10.0,1000,12000,"me"),
            Product("","item 2",10.0,1000,12000,"me"),
            Product("","item 3",10.0,1000,12000,"me"),
            Product("","item 4",10.0,1000,12000,"me"),
            Product("","item 2",10.0,1000,12000,"me"),
            Product("","item 3",10.0,1000,12000,"me"),
            Product("","item 4",10.4,1000,12000,"me"),
            Product("","item 2",10.00000,1000,12000,"me"),
            Product("","item 3",10.456,1000,12000,"me"),
            Product("","item 4",10.0,1000,12000,"me"),
            Product("","item 2",10.0,1000,12000,"me"),
            Product("","item 3",10.0,1000,12000,"me"),
            Product("","item 4",10.0,1000,12000,"me"),
            Product("","item 2",10.0,1000,12000,"me"),
            Product("","item 3",10.0,1000,12000,"me"),
            Product("","item 4",10.0,10000,12000,"me"),)
    }
    fun getFinanciers() : List<Financier> {
        return arrayListOf(
            Financier("","me",),
            Financier("","m2")
        )
    }

    fun calculatePrice(numberOfPack: Int,numberOfItem: Int,totalPrice: Int) : Int {
        val itemNumber = numberOfPack * numberOfItem
        return totalPrice / itemNumber
    }
}