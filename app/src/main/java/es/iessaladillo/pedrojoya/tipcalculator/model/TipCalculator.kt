package es.iessaladillo.pedrojoya.tipcalculator.model

import java.lang.IllegalArgumentException
import kotlin.math.ceil


// TipCalculator class. Its constructor receives bill, percentage and diners

class TipCalculator (bill: Float, percentage: Float, diners: Int) {

    val bill: Float

    val percentage: Float

    val diners: Int

    init {

        if (bill >= 0) {

            this.bill = bill

        }

        else {

            throw IllegalArgumentException()

        }

        if (percentage in 0.00..100.00) {

            this.percentage = percentage

        }

        else {

            throw IllegalArgumentException()

        }

        if (diners >= 1) {

            this.diners = diners

        }

        else {

            throw IllegalArgumentException()

        }

    }

    fun calculateTip(): Float = bill*(percentage/100)


    fun calculateTotal(): Float = bill+calculateTip()


    fun calculatePerDiner(): Float = calculateTotal()/diners


    fun calculatePerDinerRounded(): Float = ceil(calculatePerDiner().toDouble()).toFloat()


}