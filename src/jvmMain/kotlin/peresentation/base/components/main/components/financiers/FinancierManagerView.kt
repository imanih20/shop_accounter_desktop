package peresentation.base.components.main.components.financiers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import domain.financier.model.Financier
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import peresentation.base.components.main.components.addProduct.reAssign
import peresentation.base.components.main.components.financiers.components.addFinancier
import peresentation.base.components.main.components.financiers.components.showFinanciers
import peresentation.common.components.adaptiveLayout

@Composable
fun financierManagerView() {
    val controller: FinancierViewController by inject(FinancierViewController::class.java)
    val scope = rememberCoroutineScope()
    val financierList = remember { mutableStateListOf<Financier>() }
    suspend fun refreshList() {
        controller.getFinanciers().collect {
            financierList.reAssign(it)
        }
    }
    scope.launch {
        refreshList()
    }
    adaptiveLayout(
        {
            addFinancier(it) { financier ->
                flow {
                    var isSucceed = true
                    controller.saveFinancier(financier).collect { b ->
                        isSucceed = b
                    }
                    refreshList()
                    emit(isSucceed)
                }

            }
        },
        {
            showFinanciers(
                it,
                financierList,
                { financier ->
                    scope.launch {
                        controller.updateFinancier(financier)
                        refreshList()
                    }
                },
                { id ->
                    scope.launch {
                        controller.deleteFinancier(id)
                        refreshList()
                    }
                }
            )
        }
    )
}



