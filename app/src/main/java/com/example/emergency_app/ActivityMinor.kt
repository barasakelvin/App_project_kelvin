package com.example.emergency_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityMinor : AppCompatActivity() {

    var Buttonfront: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minor)

        Buttonfront=findViewById(R.id.Btn_front)

        Buttonfront!!.setOnClickListener {
            val intent= Intent(this,Activityfeedback::class.java)
            startActivity(intent)
        }

    }
}