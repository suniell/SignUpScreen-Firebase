package com.example.assignmentcopyof1

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentcopyof1.databinding.ActivityAddcontactBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class addcontactActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddcontactBinding
    lateinit var databaseforcontact: DatabaseReference
    lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddcontactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_layout)
        var buttonOk = dialog.findViewById<Button>(R.id.btnOk)

        buttonOk.setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_background_alertbox))




        binding.btnaddcontact.setOnClickListener {
                val nameuu = binding.textviewName.text.toString()
                val mailuu = binding.textvieweMaill.text.toString()


            val user = contactUser(nameuu, mailuu)
            databaseforcontact = FirebaseDatabase.getInstance().getReference("Users")
            databaseforcontact.child(nameuu).setValue(user).addOnSuccessListener {
                binding.textviewName.text?.clear()
                binding.textvieweMaill.text?.clear()

                dialog.show()






            }.addOnFailureListener {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }

        }














    }
}