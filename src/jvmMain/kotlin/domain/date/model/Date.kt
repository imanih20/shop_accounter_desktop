package domain.date.model

data class Date(
    val year: String,
    val month: String,
    val day: String
){
    override fun toString(): String {
        return "$year/$month/$day"
    }
}
