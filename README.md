# MathOperations
Device - Pixel 2 API 34

MainActivity.kt

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
            ((correctAnswers.toInt() / allExamples) * 100).toInt().toString()
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




activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/exampleTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:background="#FFFFFF"
        android:padding="16dp"
        android:text=""
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.87"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.131" />

    <TextView
        android:id="@+id/textView3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Итого решено примеров"
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.426"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.068" />

    <TextView
        android:id="@+id/AllExamplesTxt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="0"
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.147" />

    <TextView
        android:id="@+id/textView6"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Не правильно"
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.521"
        app:layout_constraintStart_toEndOf="@+id/textView5"
        app:layout_constraintTop_toBottomOf="@+id/textView3"
        app:layout_constraintVertical_bias="0.138" />

    <TextView
        android:id="@+id/NumberWrongTxt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:text="0"
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.688"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView6"
        app:layout_constraintVertical_bias="0.0" />

    <TextView
        android:id="@+id/textView5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Правильно"
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.202"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView3"
        app:layout_constraintVertical_bias="0.138" />

    <TextView
        android:id="@+id/NumberRigthTxt"
        android:layout_width="18dp"
        android:layout_height="36dp"
        android:text="0"
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.312"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/textView5"
        app:layout_constraintVertical_bias="0.014" />

    <Button
        android:id="@+id/CheckAnswerBtn"
        android:layout_width="325dp"
        android:layout_height="50dp"
        android:enabled="false"
        android:onClick="onClickCheckAnswer"
        android:text="Проверка"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.471"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.845" />

    <Button
        android:id="@+id/StartBtn"
        android:layout_width="325dp"
        android:layout_height="50dp"
        android:layout_marginBottom="36dp"
        android:onClick="onClickStart"
        android:text="Старт"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.424"
        app:layout_constraintStart_toStartOf="parent" />

    <TextView
        android:id="@+id/TwoOperandTxt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="00"
        android:textSize="34sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.477"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.632" />

    <TextView
        android:id="@+id/FirstOperandTxt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="00"
        android:textSize="34sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.117"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.632" />

    <TextView
        android:id="@+id/OperationTxt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="+"
        android:textSize="34sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.313"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.632" />

    <TextView
        android:id="@+id/txtEqually"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="="
        android:textSize="34sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.66"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.632" />

    <TextView
        android:id="@+id/PercentageCorrectAnswersTxt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="0.00%"
        android:textSize="48sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.478"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.446" />

    <EditText
        android:id="@+id/EditNumberTxt"
        android:layout_width="78dp"
        android:layout_height="61dp"
        android:ems="10"
        android:enabled="false"
        android:inputType="number"
        android:text="00"
        android:textSize="34sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.545"
        app:layout_constraintStart_toEndOf="@+id/txtEqually"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.637" />

</androidx.constraintlayout.widget.ConstraintLayout>


