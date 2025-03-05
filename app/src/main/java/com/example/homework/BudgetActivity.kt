package com.example.homework

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class BudgetActivity : AppCompatActivity() {
    lateinit var seekBarBudget: SeekBar
    lateinit var listViewGifts: ListView
    lateinit var textViewBudget: TextView
    lateinit var filteredGifts: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_budget)

        val category = intent.getStringExtra("CATEGORY")

        seekBarBudget = findViewById(R.id.seekBarBudget)
        listViewGifts = findViewById(R.id.listViewGifts)
        textViewBudget = findViewById(R.id.textViewBudget)

        val allGiftSuggestions = when (category) {
            "Birthday" -> listOf(
                "Gift 1: Wristwatch - 300 TL",
                "Gift 2: Birthday Cake - 150 TL",
                "Gift 3: Perfume - 200 TL"
            )
            "New Year's Eve" -> listOf(
                "Gift 1: Party Hat - 50 TL",
                "Gift 2: Champagne - 250 TL",
                "Gift 3: Holiday Package - 1000 TL"
            )
            "Mother's Day" -> listOf(
                "Gift 1: Flower Bouquet - 150 TL",
                "Gift 2: Jewelry - 500 TL",
                "Gift 3: Spa Package - 700 TL"
            )
            else -> emptyList()
        }

        filteredGifts = allGiftSuggestions.toList()

        seekBarBudget.max = 1000
        seekBarBudget.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textViewBudget.text = "Budget: $progress TL"

                if (progress == 0) {
                    val snackbar = Snackbar.make(seekBar ?: return, "Please select a budget amount", Snackbar.LENGTH_INDEFINITE)
                    snackbar.setAction("Got it") {
                        snackbar.dismiss()
                    }
                    snackbar.show()
                }

                val filteredGifts = allGiftSuggestions.filter { gift ->
                    val price = gift.split("-")[1].trim().replace(" TL", "").toInt()
                    price <= progress
                }

                val adapter = ArrayAdapter(this@BudgetActivity, android.R.layout.simple_list_item_1, filteredGifts)
                listViewGifts.adapter = adapter
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        listViewGifts.setOnItemClickListener { _, _, position, _ ->
            val selectedGift = filteredGifts[position]

            Snackbar.make(listViewGifts, "You selected: $selectedGift", Snackbar.LENGTH_INDEFINITE).show()

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("GIFT", selectedGift)
            startActivity(intent)
        }
    }

}