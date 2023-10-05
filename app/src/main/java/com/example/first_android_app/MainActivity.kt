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
            val leftNumber = btnLeft.text.toString().toInt()
            val rightNumber = btnRight.text.toString().toInt()

            val backGroundView = findViewById<ConstraintLayout>(R.id.backgroundView)
            // compare which is larger
            if (leftNumber > rightNumber) {
                // change background color to green
                backGroundView.setBackgroundColor(Color.GREEN)
                // show Toast notification "Correct!"
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                // change background color to red
                backGroundView.setBackgroundColor(Color.RED)
                // show Toast notification "Incorrect :/"
                Toast.makeText(this, "Incorrect :/", Toast.LENGTH_SHORT).show()
            }
            // assign new numbers to each button

            val random = Random()
            btnLeft.text = random.nextInt(10).toString()
            while (btnLeft.text.toString() === btnRight.text.toString()) {
                btnRight.text = random.nextInt(10).toString()
            }
        }
    }
}