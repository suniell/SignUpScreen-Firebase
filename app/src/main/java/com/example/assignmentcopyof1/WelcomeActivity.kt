package com.example.assignmentcopyof1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentcopyof1.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
         val name = intent.getStringExtra(SignInActivity.KEY2)
         val mail = intent.getStringExtra(SignInActivity.KEY1)
         val userId = intent.getStringExtra(SignInActivity.KEY3)

        binding.tvWelcome.text= "Welcome " + name
        binding.tvMail.text = "Mail: " + mail
        binding.tvuniqueId.text = "userId: " + userId

        binding.btnaddcontact.setOnClickListener {
          val addContactIntent = Intent(this, addcontactActivity::class.java)
            startActivity(addContactIntent)
        }









    }
}