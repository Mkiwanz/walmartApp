package com.example.walmartapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class ActivityLogin : ComponentActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var btnRegister: Button
    private val users = arrayListOf(
        User("Mohammad", "Kiwan", "m", "123")
    )

    companion object {
        const val REGISTER_ACTIVITY_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.editEmail)
        etPassword = findViewById(R.id.editPassword)
        btnSignIn = findViewById(R.id.buttonSignIn)
        btnRegister =
            findViewById(R.id.textCreateAccount) // Assuming you have this button in your layout

        btnSignIn.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val user = users.find { it.username == username && it.password == password }

            if (user != null) {
                val intent = Intent(this, ShoppingCategoryActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
        val tvForgetPassword: TextView = findViewById(R.id.textForgotPassword)
        tvForgetPassword.setOnClickListener {
            sendPasswordEmail()
        }
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, REGISTER_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REGISTER_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.getSerializableExtra("newUser")?.let { newUser ->
                val user = newUser as User
                users.add(user)
                Toast.makeText(
                    this,
                    "User ${user.firstname} registered successfully!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun sendPasswordEmail() {
        val emailInputDialog = AlertDialog.Builder(this)
        val input = EditText(this)
        input.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        emailInputDialog.setView(input)
        emailInputDialog.setPositiveButton("Submit") { dialog, which ->
            val email = input.text.toString()
            val user = users.find { it.username == email }
            user?.let {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "message/rfc822"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    putExtra(Intent.EXTRA_SUBJECT, "Your Password")
                    putExtra(Intent.EXTRA_TEXT, "Your password is: ${user.password}")
                }
                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."))
                } catch (ex: android.content.ActivityNotFoundException) {
                    Toast.makeText(
                        this@ActivityLogin,
                        "There are no email clients installed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } ?: run {
                Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show()
            }
        }
        emailInputDialog.setNegativeButton("Cancel", { dialog, which -> dialog.cancel() })
        emailInputDialog.show()
    }

}
