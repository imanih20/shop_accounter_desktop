package common.utils

import domain.date.model.Date
import ir.huri.jcal.JalaliCalendar

object DateUtils {
    fun getCurrentDate() : Date{
        val calendar = JalaliCalendar()
        return Date(calendar.year.toString(),calendar.month.toString(),calendar.day.toString())
    }
}