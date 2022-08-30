package com.example.emergency_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var ButtonNext: Button?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButtonNext=findViewById(R.id.Btn_next)

        ButtonNext!!.setOnClickListener {
            val intent= Intent(this,Activitychoice::class.java)
            startActivity(intent)
        }
    }
}