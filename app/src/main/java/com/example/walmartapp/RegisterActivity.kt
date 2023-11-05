package com.example.walmartapp

import androidx.activity.ComponentActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.Serializable

class RegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val firstNameEditText = findViewById<EditText>(R.id.editFirstName)
        val lastNameEditText = findViewById<EditText>(R.id.editLastName)
        val emailEditText = findViewById<EditText>(R.id.editEmail)
        val passwordEditText = findViewById<EditText>(R.id.editPassword)
        val createAccountButton = findViewById<Button>(R.id.buttonCreateAccount)

        createAccountButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            } else {
                val newUser = User(firstName, lastName, email, password)
                Toast.makeText(this, "Account created successfully.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ActivityLogin::class.java)
                intent.putExtra("newUser", newUser as Serializable)
                startActivity(intent)

                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    // Create a User data class to hold the user data
    data class User(val firstName: String, val lastName: String, val email: String, val password: String)
}
