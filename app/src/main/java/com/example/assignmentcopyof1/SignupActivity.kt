package com.example.assignmentcopyof1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentcopyof1.databinding.ActivitySignupBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val mail = binding.etMail.text.toString()
            val uniqueid = binding.etUsername.text.toString()
            val userpassword = binding.etPassword.text.toString()

            val user = User(name, mail, userpassword, uniqueid)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueid).setValue(user).addOnSuccessListener {
                binding.etMail.text?.clear()
                binding.etName.text?.clear()
                binding.etUsername.text?.clear()
                binding.etPassword.text?.clear()



                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()


            }.addOnFailureListener {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }


        }

        binding.tvSignIn.setOnClickListener {
            val openSignInActivity = Intent(this, SignInActivity::class.java)
            startActivity(openSignInActivity)
        }


    }
}