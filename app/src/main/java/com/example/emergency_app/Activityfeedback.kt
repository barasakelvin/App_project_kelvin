package com.example.emergency_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Activityfeedback : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference : DatabaseReference

    lateinit var Txt_name: EditText
    lateinit var Txt_fnum: EditText
    lateinit var Txt_email: EditText
    lateinit var Txt_loc: EditText
    lateinit var Txt_des: EditText
    lateinit var Btn_submit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activityfeedback)

        //INITIALISE AUTHENTICATION
        auth = FirebaseAuth.getInstance()

        Txt_name = findViewById(R.id.Txt_name)
        Txt_fnum = findViewById(R.id.Txt_fnum)
        Txt_email = findViewById(R.id.Txt_email)
        Txt_loc = findViewById(R.id.Txt_loc)
        Txt_des = findViewById(R.id.Txt_des)
        Btn_submit = findViewById(R.id.Btn_submit)


        Btn_submit.setOnClickListener {
            val user_name = Txt_name.text.toString().trim()
            val user_number = Txt_fnum.text.toString().trim()
            val user_email = Txt_email.text.toString().trim()
            val user_location = Txt_loc.text.toString().trim()
            val user_description = Txt_des.text.toString().trim()
            val id = System.currentTimeMillis().toString()
            //CHECK FOR EMPTY FIELDS
            if (user_name.isEmpty()) {
                Txt_name.setError("Please fill out this field")
                Txt_name.requestFocus()
            } else if (user_email.isEmpty()) {
                Txt_email.setError("Please fill out this input")
                Txt_email.requestFocus()
            } else if (user_number.isEmpty()) {
                Txt_fnum.setError("Please fill out this input")
                Txt_fnum.requestFocus()
            } else if (user_location.isEmpty()) {
                Txt_loc.setError("Please fill out this input")
                Txt_loc.requestFocus()
            } else if (user_description.isEmpty()) {
                Txt_des.setError("Please fill out this input")
                Txt_des.requestFocus()
            } else {
                //PROCEED TO SAVE DATA
                //START BY CREATING USER OBJECT
                val userData =User(
                    user_name,
                    user_email,
                    user_location,
                    user_number,
                    user_description,
                    id,
                    isAdmin = 0
                )
                //CREATE A REFERENCE TO THE DATABASE TO STORE DATA
                val reference = FirebaseDatabase.getInstance().getReference().child("Users/$id")
                //START SAVING USERDATA
                reference.setValue(userData).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val goToDash =
                            Intent(applicationContext,Activityfeedback ::class.java)
                        startActivity(goToDash)
                        Toast.makeText(
                            applicationContext,
                            "Details saved successfully",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    }else{
                        Toast.makeText(this,"Failed to send",Toast.LENGTH_LONG).show()
                        finish()
                    }

                }
            }
        }
    }
}