package com.example.emergency_app

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ActivityCritical : AppCompatActivity() {

    lateinit var ButtonEmail: Button
    lateinit var ButtonSms: Button
    lateinit var ButtonCall: Button
    lateinit var ButtonDial: Button
    lateinit var ButtonShare: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_critical)

        ButtonEmail=findViewById(R.id.Btn_email)
        ButtonSms=findViewById(R.id.Btn_sms)
        ButtonCall=findViewById(R.id.Btn_call)
        ButtonDial=findViewById(R.id.Btn_dial)
        ButtonShare=findViewById(R.id.Btn_share)

        ButtonSms!!.setOnClickListener {
            val uri = Uri.parse("smsto:0703548736")

            val intent = Intent(Intent.ACTION_SENDTO, uri)

            intent.putExtra("sms_body", "Emergency! Please help!")

            startActivity(intent)
        }
        ButtonEmail!!.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "emergencyservices@gmail.com", null))

            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")

            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")

            startActivity(Intent.createChooser(emailIntent, "Send email..."))

        }
        ButtonDial!!.setOnClickListener {
            val phone = "+254703548736"

            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))

            startActivity(intent)


        }
        ButtonCall!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+254703548736"))

            if (ContextCompat.checkSelfPermission(
                    this@ActivityCritical,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@ActivityCritical,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }

        }
        ButtonShare!!.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Hey,do you need quicker response to emergencies? Download this app then!")
            startActivity(shareIntent)
        }
    }
}
