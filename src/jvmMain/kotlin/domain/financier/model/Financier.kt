package domain.financier.model

data class Financier(val id :String = "",val name: String = "",val description: String = ""){
    override fun toString(): String {
        return name
    }
}