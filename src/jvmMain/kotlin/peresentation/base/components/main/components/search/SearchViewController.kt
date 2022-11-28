package peresentation.base.components.main.components.search

import common.utils.Controller
import domain.product.model.Product

class SearchViewController : Controller {
    fun searchProducts(query: String, owner: String) : List<Product>{
        return listOf(
            Product("","item 1",10.0,1000,1200,"me"),
            Product("","item 2",10.0,1000,1200,"me"),
        )
    }
}