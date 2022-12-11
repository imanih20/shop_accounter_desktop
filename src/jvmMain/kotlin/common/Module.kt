package common

import org.koin.dsl.module
import peresentation.base.components.main.components.addProduct.AddProductController
import peresentation.base.components.main.components.financiers.FinancierViewController
import peresentation.base.components.main.components.saleProduct.SaleProductController
import peresentation.base.components.main.components.search.SearchViewController
import peresentation.base.components.main.components.statistics.StatisticsController

val appModule = module {
    factory { AddProductController(get(),get(),get(),get(),get(),get()) }

    factory { SaleProductController(get(),get(),get(),get(),get(),get(),get()) }

    factory { FinancierViewController(get(),get(), get(), get()) }

    factory { StatisticsController(get(),get(),get()) }

    factory { SearchViewController(get(),get(),get(),get()) }

}