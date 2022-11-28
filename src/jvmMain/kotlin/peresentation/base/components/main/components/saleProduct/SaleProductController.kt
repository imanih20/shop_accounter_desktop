package peresentation.base.components.main.components.saleProduct

import common.utils.Controller
import domain.product.model.Product
import domain.sale.model.Sale

class SaleProductController : Controller{
    fun getAllProducts() : List<Product>{
        return arrayListOf(Product("","item",3.0,1000,1200,""))
    }

    fun calculateTotalPrice(quantity: Int, price: Int, discount: Int) : Int {
        val totalPrice = quantity*price
        return totalPrice - totalPrice*(discount/100)
    }

    fun getDiscount(pid: String) : Int{
        return 0
    }

    fun getSales() : List<Sale>{
        return listOf()
    }
}