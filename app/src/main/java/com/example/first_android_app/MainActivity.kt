package com.example.first_android_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Random

class MainActivity : AppCompatActivity() {

    // application data using a list of Pairs
    private val listOfPairs = listOf(
        Pair("8 % 5", 3),
        Pair("4 % 10", 4),
        Pair("9 % 9", 0),
        Pair("24 % 2", 0),
        Pair("3 % 2", 1),
        Pair("10 % 4", 2),
        Pair("7 % 4", 3),
        Pair("16 % 9", 7),
        Pair("11 % 5", 6),
        Pair("33 % 12", 9),
        Pair("18 % 10", 8),
        Pair("12 % 7", 5)
    )

    private var leftIndex = 0
    private var rightIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // we need to assign data to each button on create so we aren't
        //  always starting with the same values. Get the buttons first

        // every widget in Android will have an id "attribute" (same term from HTML for XML?)
        // select each button using its id (findViewById basically like getElementById)
        val btnLeft = findViewById<Button>(R.id.btnLeft)
        val btnRight = findViewById<Button>(R.id.btnRight)

        // once we have the buttons selected, we can set the text value for each
        setTextValuesFromData(btnLeft, btnRight)

        // .setOnClickListener (addEventListener)
        btnLeft.setOnClickListener {
            // when left button is clicked, get both number values
            handleButtonClick(btnLeft, btnRight, true)
            // set new expressions to buttons
            setTextValuesFromData(btnLeft, btnRight)

            // assign new numbers to each button
            // resetNumbers(btnLeft, btnRight)
        }

        btnRight.setOnClickListener {
            // when left button is clicked, get both number values
            handleButtonClick(btnLeft, btnRight, false)
            // set new expressions to buttons
            setTextValuesFromData(btnLeft, btnRight)

            // assign new numbers to each button
            // resetNumbers(btnLeft, btnRight)
        }
    }

    private fun setTextValuesFromData(leftButton: Button?, rightButton: Button?) {
        val random = Random()
        // get a random index between 0 and size of listOfPairs (exclusive)
        leftIndex = random.nextInt(listOfPairs.size)
        do {
            rightIndex = random.nextInt(listOfPairs.size)
        } while (leftIndex == rightIndex || listOfPairs[leftIndex].second == listOfPairs[rightIndex].second)

        leftButton?.text = listOfPairs[leftIndex].first
        rightButton?.text = listOfPairs[rightIndex].first

//        leftButton?.text = random.nextInt(10).toString()
//        rightButton?.text = random.nextInt(10).toString()
//        while (leftButton?.text.toString() === rightButton?.text.toString()) {
//            rightButton?.text = random.nextInt(10).toString()
//        }
    }


    private fun handleButtonClick(leftButton: Button, rightButton: Button, isLeftButton: Boolean) {
        // originally we checked to see if each number was bigger
        // now we need to know which second value is different.
        // for now, we'll use the index variables to access each Pair

        val leftNumber = listOfPairs[leftIndex].second
        val rightNumber = listOfPairs[rightIndex].second

        val toastMessage: String = if (isLeftButton) {
            if (leftNumber > rightNumber) respondCorrect()
            else respondIncorrect()
        } else {
            if (rightNumber > leftNumber) respondCorrect()
            else respondIncorrect()
        }

        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }

//    fun resetNumbers(leftButton: Button, rightButton: Button) {
//        val random = Random()
//        leftButton.text = random.nextInt(10).toString()
//        rightButton.text = random.nextInt(10).toString()
//        while (leftButton.text.toString() === rightButton.text.toString()) {
//            rightButton.text = random.nextInt(10).toString()
//        }
//    }

    private fun respondCorrect(): String {
        findViewById<ConstraintLayout>(R.id.backgroundView).setBackgroundColor(Color.GREEN)
        return "Correct!"
    }

    private fun respondIncorrect(): String {
        findViewById<ConstraintLayout>(R.id.backgroundView).setBackgroundColor(Color.RED)
        return "Incorrect :/"
    }
}