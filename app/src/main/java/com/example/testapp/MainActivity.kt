package com.example.testapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //инициализация переменных
    private lateinit var exampleTextView: TextView
    private lateinit var firstOperandTxt: TextView
    private lateinit var twoOperandTxt: TextView
    private lateinit var operationTxt: TextView
    private lateinit var allExamplesTxt: TextView
    private lateinit var numberRightTxt: TextView
    private lateinit var numberWrongTxt: TextView
    private lateinit var percentageCorrectAnswersTxt: TextView
    private lateinit var editNumberTxt: EditText
    private lateinit var checkAnswerBtn: Button
    private lateinit var startBtn: Button

    private var allExamples = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exampleTextView = findViewById(R.id.exampleTextView)
        firstOperandTxt = findViewById(R.id.FirstOperandTxt)
        twoOperandTxt = findViewById(R.id.TwoOperandTxt)
        operationTxt = findViewById(R.id.OperationTxt)
        allExamplesTxt = findViewById(R.id.AllExamplesTxt)
        numberRightTxt = findViewById(R.id.NumberRigthTxt)
        numberWrongTxt = findViewById(R.id.NumberWrongTxt)
        percentageCorrectAnswersTxt = findViewById(R.id.PercentageCorrectAnswersTxt)
        editNumberTxt = findViewById(R.id.EditNumberTxt)
        checkAnswerBtn = findViewById(R.id.CheckAnswerBtn)
        startBtn = findViewById(R.id.StartBtn)
    }

    //функция по нажатию на кнопку старт
    //выбирается рандомное значение из списка для оператора примера
    //присваивается рандомное значение для каждого операнда от 10 до 99
    fun onClickStart(view: View) {
        val operands = arrayOf("+", "-", "*", "/")
        val operand = operands.random()
        var operand1 = (10..99).random()
        var operand2 = (10..99).random()

        //проверка целочисленного деления
        if (operand == "/") {
            while (operand1 % operand2 != 0) {
                operand1 = (10..99).random()
                operand2 = (10..99).random()
            }
        }

        exampleTextView.text = ""
        firstOperandTxt.text = operand1.toString()
        twoOperandTxt.text = operand2.toString()
        operationTxt.text = operand
        editNumberTxt.text.clear()
        editNumberTxt.setBackgroundColor(Color.WHITE)
        editNumberTxt.isEnabled = true
        checkAnswerBtn.isEnabled = true
        startBtn.isEnabled = false
    }

    /*функция проверки ответа
    1.перевод переменных из текста в число
    2.
    */
    fun onClickCheckAnswer(view: View) {
        val operator1 = firstOperandTxt.text.toString().toInt()
        val operator2 = twoOperandTxt.text.toString().toInt()
        val operatorTxt = operationTxt.text.toString()
        val resultPlayer = editNumberTxt.text.toString().toInt()

        if (checkResult(operatorTxt, operator1, operator2, resultPlayer)) {
            correctAnswers++
        }

        allExamples++
        updateStatistics()
        resetFields()
    }

    /*функция проверки правильности результата
    1.создается переменная result(ожидаемый результат) и переменная playerResult(результат пользователя)
    2.
    */
    private fun checkResult(operator: String, operatorOne: Int, operatorTwo: Int, playerResult: Int): Boolean {
        val result = when (operator) {
            "+" -> operatorOne + operatorTwo
            "-" -> operatorOne - operatorTwo
            "*" -> operatorOne * operatorTwo
            "/" -> operatorOne / operatorTwo
            else -> 0
        }
        return (result == playerResult)
    }

    //функция обновления статистики
    private fun updateStatistics() {
        allExamplesTxt.text = allExamples.toString()
        numberRightTxt.text = correctAnswers.toString()
        numberWrongTxt.text = (allExamples - correctAnswers).toString()

        val percentage = if (allExamples > 0) {
            ((correctAnswers.toDouble() / allExamples) * 100).toInt().toString()
        } else {
            "0"
        }
        percentageCorrectAnswersTxt.text = "$percentage%"
    }

    //сброс полей
    private fun resetFields() {
        editNumberTxt.setBackgroundColor(Color.TRANSPARENT)
        editNumberTxt.isEnabled = false
        checkAnswerBtn.isEnabled = false
        startBtn.isEnabled = true
    }
}

