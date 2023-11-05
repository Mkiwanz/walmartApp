package com.example.walmartapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class ShoppingCategoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_activity)

        val username = intent.getStringExtra("username")
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        tvWelcome.text = "Welcome, "+ username;

        val ivClothing = findViewById<ImageView>(R.id.ivClothing)
        ivClothing.setOnClickListener {
            Toast.makeText(this, "You have chosen the Clothing category of shopping", Toast.LENGTH_LONG).show()
        }

        val ivElectronics = findViewById<ImageView>(R.id.ivElectronics)
        ivElectronics.setOnClickListener {
            Toast.makeText(this, "You have chosen the Electronics category of shopping", Toast.LENGTH_LONG).show()
        }

        val ivBeauty = findViewById<ImageView>(R.id.ivBeauty)
        ivBeauty.setOnClickListener {
            Toast.makeText(this, "You have chosen the Beauty category of shopping", Toast.LENGTH_LONG).show()
        }

        val ivFood = findViewById<ImageView>(R.id.ivFood)
        ivFood.setOnClickListener {
            Toast.makeText(this, "You have chosen the Food category of shopping", Toast.LENGTH_LONG).show()
        }
    }
}