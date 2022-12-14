package common.utils

import kotlin.text.StringBuilder

object TextUtils {
    fun doubleNumberString(text: String) : String{
        val stringBuilder = StringBuilder()
        val chars = text.toCharArray()
        var dotCount = 0
        chars.forEach {
            if (it.isDigit() || it == '.'){
                if (it=='.'){
                    dotCount++
                    if (dotCount<=1) {
                        stringBuilder.append(it)
                    }
                } else {
                    stringBuilder.append(it)
                }

            }
        }
        return stringBuilder.toString()
    }
    fun onlyNumberInput(text: String) : String{
        val stringBuilder = StringBuilder()
        val chars = text.toCharArray()
        for (c in chars){
            if (c == '.'){
                break
            }
            if (c.isDigit()){
                stringBuilder.append(c)
            }
        }
        return stringBuilder.toString()
    }
    fun showNumberString(text: String): String {
        val stringBuilder = StringBuilder()
        val chars = text.toCharArray()
        var dotCount = 0
        var dotCaptured = false
        val dotAfter = StringBuilder()
        chars.forEach {
            if (it.isDigit() || (it == '.' && dotCount < 1)) {
                if (dotCaptured) dotAfter.append(it)
                if (it == '.') {
                    dotCount++
                    dotCaptured = true
                }
                if (!dotCaptured) stringBuilder.append(it)
            }
        }
        return if (!isZero(dotAfter.toString()))
            "$stringBuilder.$dotAfter"
        else
            stringBuilder.toString()
    }

    private fun isZero(number: String) : Boolean{
        val chars = number.toCharArray()
        var isZero = true
        if (chars.isEmpty()) return true
        chars.forEach {
            if (!(it.isDigit() && it == '0'))
                isZero = false

        }
        return isZero
    }

    fun isNumber(text: String) : Boolean {
        val chars = text.toCharArray()
        var isNumber = true
        chars.forEach {
            if (!it.isDigit())
                isNumber = false
        }
        return isNumber
    }
    fun addSeparator(number: String) : String{
        val chars = number.toCharArray()
        val builder = StringBuilder()
        var i = chars.size-1
        var counter = 0
        while (i>=0){
            counter++
            builder.append(chars[i])
            if (counter % 3 == 0 && i>0){
                builder.append("،")
            }
            i--
        }
        return builder.reverse().toString()
    }
}