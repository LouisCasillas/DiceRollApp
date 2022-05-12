package com.example.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

data class Dice (var min_num : Int = 1, var max_num : Int = 6) {

    fun rollDice() : Int
    {
        if (min_num > max_num) {
            min_num = max_num.also { max_num = min_num }
        }

        return (min_num .. max_num).random()
    }
}

class MainActivity : AppCompatActivity() {

    private val dice : Dice = Dice()

    private var et_min_number : EditText? = null
    private var et_max_number : EditText? = null
    private var tv_dice_result : TextView? = null
    private var btn_roll : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_min_number = findViewById<EditText>(R.id.et_min_number)
        et_max_number = findViewById<EditText>(R.id.et_max_number)
        tv_dice_result = findViewById<TextView>(R.id.tv_dice_result)
        btn_roll = findViewById<Button>(R.id.btn_roll)


        et_min_number!!.setText(dice.min_num.toString())
        et_max_number!!.setText(dice.max_num.toString())

        btn_roll!!.setOnClickListener {

            dice.min_num = Integer.parseInt(et_min_number!!.text.toString())
            dice.max_num = Integer.parseInt(et_max_number!!.text.toString())

            val dice_roll = dice.rollDice()

            tv_dice_result!!.setText(dice_roll.toString())

            Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
        }
    }
}