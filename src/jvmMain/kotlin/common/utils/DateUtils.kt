package common.utils

import ir.huri.jcal.JalaliCalendar

object DateUtils {
    private val calendar = JalaliCalendar()

    private fun isValidDate(date: String) : Boolean{
        val splitDate = date.split("/")
        var isValid = true
        if (splitDate.size == 3){
            splitDate.forEach {
                if (!TextUtils.isNumber(it)) isValid = false
            }
        }
        return isValid
    }
    fun isPast(date: String) : Boolean{
        if (!isValidDate(date)) return true

        val splitDate = date.split("/")
        val year = splitDate[0].toInt()
        val month = splitDate[1].toInt()
        val day = splitDate[2].toInt()

        if (year < calendar.year) return true
        else if (year == calendar.year) {
            if (month < calendar.month) return true
            else if (month == calendar.month){
                if (day < calendar.day) return true
            }
        }
        return false
    }

    fun isFuture(date: String) : Boolean{
        if (!isValidDate(date)) return true

        val splitDate = date.split("/")
        val year = splitDate[0].toInt()
        val month = splitDate[1].toInt()
        val day = splitDate[2].toInt()

        if (year > calendar.year) return true
        else if (year == calendar.year) {
            if (month > calendar.month) return true
            else if (month == calendar.month){
                if (day > calendar.day) return true
            }
        }
        return false
    }
}