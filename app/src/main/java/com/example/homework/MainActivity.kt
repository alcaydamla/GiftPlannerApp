package com.example.homework

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var spinnerCategory: Spinner
    lateinit var btnNext: Button
    lateinit var tvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitle = findViewById(R.id.tvTitle)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        btnNext = findViewById(R.id.btnProceed)


        val blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink_animation)
        tvTitle.startAnimation(blinkAnimation)

        val categories = listOf("Birthday", "New Year's Eve", "Mother's Day")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter


        btnNext.setOnClickListener {
            val selectedCategory = spinnerCategory.selectedItem.toString()

            if (selectedCategory.isNotEmpty()) {
                val intent = Intent(this, BudgetActivity::class.java)
                intent.putExtra("CATEGORY", selectedCategory)
                startActivity(intent)
            } else {

                Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
