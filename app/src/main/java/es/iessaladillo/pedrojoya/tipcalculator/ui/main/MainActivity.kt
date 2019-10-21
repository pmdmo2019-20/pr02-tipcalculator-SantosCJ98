package es.iessaladillo.pedrojoya.tipcalculator.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import es.iessaladillo.pedrojoya.tipcalculator.R
import es.iessaladillo.pedrojoya.tipcalculator.model.TipCalculator
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()


    }

    private fun setupViews() {
        refresh(txtBill)
        refresh(txtDiners)
        refresh(txtPercentage)
        btnResetTip.setOnClickListener {resetFirstPart()}
        btnResetDiners.setOnClickListener {resetSecondPart()}
    }

    private fun calculate() {

        val bill: Float?

        val percentage: Float?

        val diners: Int?

        if (isValidBill()) {

            bill = txtBill.text.toString().toFloat()

        }

        else {

            bill = getString(R.string.defaultValue).toFloat()

            txtBill.setText(getString(R.string.format, bill).replace(',', '.'))




        }

        if (isValidPercentage()) {

            percentage = txtPercentage.text.toString().toFloat()

        }

        else {

            percentage = getString(R.string.defaultPer).toFloat()

            txtPercentage.setText(getString(R.string.format, percentage).replace(',', '.'))

        }

        if (isValidDinners()) {

            diners = txtDiners.text.toString().toInt()

        }

        else {

            diners = getString(R.string.defaultDiners).toInt()

            txtDiners.setText(diners.toString())

        }


        useCalculator(bill, percentage, diners)



    }

    private fun refresh(edittext: EditText) {

        edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                calculate()

            }
        })

    }

    private fun resetFirstPart() {

        txtBill.setText(getString(R.string.defaultValue).toString())

        txtPercentage.setText(getString(R.string.defaultPer).toString())

        val bill = txtBill.text.toString().toFloat()

        val percentage = txtPercentage.text.toString().toFloat()

        val diners = txtDiners.text.toString().toInt()

        useCalculator(bill, percentage, diners)

        txtBill.requestFocus()

    }

    private fun resetSecondPart() {

        val bill = txtBill.text.toString().toFloat()

        val percentage = txtPercentage.text.toString().toFloat()

        txtDiners.setText(getString(R.string.defaultDiners).toString())

        val diners = txtDiners.text.toString().toInt()

        useCalculator(bill, percentage, diners)

        txtDiners.requestFocus()



    }

    private fun isFloat (numero: String): Boolean {

        try {

            numero.toFloat()

            return true

        }

        catch (e: NumberFormatException) {

            return false

        }

    }

    private fun isInt (numero: String): Boolean {

        try {

            numero.toInt()

            return true

        }

        catch (e: NumberFormatException) {

            return false

        }

    }

    private fun isValidBill(): Boolean {

        if (!TextUtils.isEmpty(txtBill.text.toString())) {

            if (isFloat(txtBill.text.toString())) {

                if (txtBill.text.toString().toFloat() >= 0) {

                    return true


                } else {

                    return false


                }

            } else {

                return false


            }

        } else {

            return false


        }

    }

    private fun isValidPercentage(): Boolean {

        if (!TextUtils.isEmpty(txtPercentage.text.toString())) {

            if (isFloat(txtPercentage.text.toString())) {

                if (txtPercentage.text.toString().toFloat() in 0.00..100.00) {

                    return true

                } else {

                    return false

                }

            }

            else {

                return false


            }

        }

        else {

            return false

        }

    }

    private fun isValidDinners(): Boolean {

        if (!TextUtils.isEmpty(txtDiners.text.toString())) {

            if (isInt(txtDiners.text.toString())) {

                if (txtDiners.text.toString().toInt() >= 0) {

                    return true

                } else {

                    return false

                }

            } else {

                return false
            }

        } else {

            return false

        }

    }


    private fun useCalculator(bill: Float, percentage: Float, diners: Int) {

        val tipCalculator = TipCalculator(bill, percentage, diners)

        txtTip.setText(getString(R.string.format, tipCalculator.calculateTip()).replace(',', '.'))

        txtTotal.setText(getString(R.string.format, tipCalculator.calculateTotal()).replace(',', '.'))

        txtPerDiner.setText(getString(R.string.format, tipCalculator.calculatePerDiner()).replace(',', '.'))

        txtPerDinerRounded.setText(getString(R.string.format, tipCalculator.calculatePerDinerRounded()).replace(',', '.'))




    }


}
