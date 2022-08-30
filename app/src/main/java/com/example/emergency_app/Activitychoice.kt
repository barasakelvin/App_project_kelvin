package com.example.emergency_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Activitychoice : AppCompatActivity() {
    var ButtonMinor: Button?= null
    var ButtonCritical: Button?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activitychoice)

        ButtonMinor=findViewById(R.id.Btn_minor)
        ButtonCritical=findViewById(R.id.Btn_critical)

        ButtonMinor!!.setOnClickListener {
            val intent= Intent(this,ActivityMinor::class.java)
            startActivity(intent)
        }
        ButtonCritical!!.setOnClickListener {
            val intent= Intent(this,ActivityCritical::class.java)
            startActivity(intent)
        }
    }
}