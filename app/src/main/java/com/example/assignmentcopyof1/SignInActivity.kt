package com.example.assignmentcopyof1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentcopyof1.databinding.ActivitySignInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    lateinit var databaseReference: DatabaseReference

    companion object {
        const val KEY1 = "com.example.assignmentcopyof1.SignInActivity.mail"
        const val KEY2 = "com.example.assignmentcopyof1.SignInActivity.name"
        const val KEY3 = "com.example.assignmentcopyof1.SignInActivity.userId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSignIn.setOnClickListener {
            // take reference till "Users" node
            val userNamee = binding.UsernameEt.text.toString()

            if (userNamee.isNotEmpty()) {
                readData(userNamee)
            } else {
                Toast.makeText(this, "enter Username ", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun readData(userNamee: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userNamee).get().addOnSuccessListener {
            if (it.exists()) {
                // welcome user in your APP
                val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueid").value

                val intentwelcome = Intent(this, WelcomeActivity::class.java)
                intentwelcome.putExtra(KEY1, email.toString())
                intentwelcome.putExtra(KEY2, name.toString())
                intentwelcome.putExtra(KEY3, userId.toString())
                startActivity(intentwelcome)


            } else {
                Toast.makeText(this, "user doesn't exist", Toast.LENGTH_SHORT).show()

            }

        }

            .addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }

    }

}