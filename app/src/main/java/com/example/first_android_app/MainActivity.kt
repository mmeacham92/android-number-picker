package com.example.first_android_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // every widget in Android will have an id "attribute" (same term from HTML for XML?)
        // select each button using its id (findViewById basically like getElementById)
        val btnLeft = findViewById<Button>(R.id.btnLeft)
        val btnRight = findViewById<Button>(R.id.btnRight)

        // .setOnClickListener (addEventListener)
        btnLeft.setOnClickListener {
            // when left button is clicked, get both number values
            handleButtonClick(btnLeft, btnRight, true)
            // assign new numbers to each button
            resetNumbers(btnLeft, btnRight);
        }

        btnRight.setOnClickListener {
            // when left button is clicked, get both number values
            handleButtonClick(btnLeft, btnRight, false)
            // assign new numbers to each button
            resetNumbers(btnLeft, btnRight);
        }
    }

    fun handleButtonClick(leftButton: Button, rightButton: Button, isLeftButton: Boolean) {
        val leftNumber = leftButton.text.toString().toInt()
        val rightNumber = rightButton.text.toString().toInt()
        val backGroundView = findViewById<ConstraintLayout>(R.id.backgroundView)
        var backGroundColor: Int
        var toastMessage: String
        if (isLeftButton) {
            if (leftNumber > rightNumber) {
                backGroundColor = Color.GREEN
                toastMessage = "Correct!"
            } else {
                backGroundColor = Color.RED
                toastMessage = "Incorrect :/"
            }
        } else {
            if (rightNumber > leftNumber) {
                backGroundColor = Color.GREEN
                toastMessage = "Correct!"
            } else {
                backGroundColor = Color.RED
                toastMessage = "Incorrect :/"
            }
        }
        backGroundView.setBackgroundColor(backGroundColor)
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }

    fun resetNumbers(leftButton: Button, rightButton: Button) {
        val random = Random()
        leftButton.text = random.nextInt(10).toString()
        rightButton.text = random.nextInt(10).toString()
        while (leftButton.text.toString() === rightButton.text.toString()) {
            rightButton.text = random.nextInt(10).toString()
        }
    }
}