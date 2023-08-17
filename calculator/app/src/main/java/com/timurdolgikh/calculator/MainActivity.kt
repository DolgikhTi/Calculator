package com.timurdolgikh.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bth_0.setOnClickListener { setTextFields("0") }
        bth_1.setOnClickListener { setTextFields("1") }
        bth_2.setOnClickListener { setTextFields("2") }
        bth_3.setOnClickListener { setTextFields("3") }
        bth_4.setOnClickListener { setTextFields("4") }
        bth_5.setOnClickListener { setTextFields("5") }
        bth_6.setOnClickListener { setTextFields("6") }
        bth_7.setOnClickListener { setTextFields("7") }
        bth_8.setOnClickListener { setTextFields("8") }
        bth_9.setOnClickListener { setTextFields("9") }
        minus_bth.setOnClickListener { setTextFields("-") }
        plus_bth.setOnClickListener { setTextFields("+") }
        mult_bth.setOnClickListener { setTextFields("*") }
        divide_bth.setOnClickListener { setTextFields("/") }
        bracket_open_bth.setOnClickListener { setTextFields("(") }
        bracket_close_bth.setOnClickListener { setTextFields(")") }

        clear_bth.setOnClickListener {
            math_operation.text = ""
            result_text.text = ""
        }

        back_bth.setOnClickListener {
            val str = math_operation.text.toString()
            if (str.isNotEmpty())
                math_operation.text = str.substring(0, str.length - 1)

            result_text.text = ""
        }

        equal_bth.setOnClickListener {
            try {

                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if (result == longRes.toDouble())
                    result_text.text = longRes.toString()
                else
                    result_text.text = result.toString()
            } catch (e:Exception) {
                Log.d("Ошибка", "сообщение: ${e.message}")
            }
        }
    }

    fun setTextFields(str: String) {
        if (result_text.text != "") {
            math_operation.text = result_text.text
            result_text.text = ""
        }

        math_operation.append(str)
    }
}