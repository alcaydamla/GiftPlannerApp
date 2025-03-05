package com.example.homework

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    lateinit var btnExit: Button
    lateinit var btnAddToWishlist: Button
    lateinit var btnEditGift: Button // Yeni Edit Button
    lateinit var textViewGiftDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val giftDetail = intent.getStringExtra("GIFT") // Get the selected gift

        textViewGiftDetail = findViewById(R.id.textViewGiftDetail)
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist)
        btnExit = findViewById(R.id.btnExit)
        btnEditGift = findViewById(R.id.btnEditGift) // Edit Button'ı tanımladık

        textViewGiftDetail.text = "Selected Gift: $giftDetail"

        btnAddToWishlist.setOnClickListener {
            Snackbar.make(it, "Gift saved to wishlist!", Snackbar.LENGTH_LONG).show()
        }

        btnEditGift.setOnClickListener {
            showEditGiftDialog(giftDetail)
        }

        btnExit.setOnClickListener {
            Snackbar.make(it, "Going back to main screen", Snackbar.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showEditGiftDialog(currentGiftDetail: String?) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        val etGiftNote = dialogView.findViewById<EditText>(R.id.etGiftNote)
        val btnSaveNote = dialogView.findViewById<Button>(R.id.btnSaveNote)

        etGiftNote.setText(currentGiftDetail)

        btnSaveNote.setOnClickListener {
            val updatedGiftDetail = etGiftNote.text.toString()
            if (updatedGiftDetail.isNotEmpty()) {
                textViewGiftDetail.text = "Selected Gift: $updatedGiftDetail"

                Snackbar.make(
                    btnSaveNote,
                    "Gift updated successfully!",
                    Snackbar.LENGTH_LONG
                ).show()
                
                Toast.makeText(this, "Gift detail updated!", Toast.LENGTH_SHORT).show()

                dialog.dismiss() // Dialog'u kapat
            } else {
                Toast.makeText(this, "Please enter a valid gift detail", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}
