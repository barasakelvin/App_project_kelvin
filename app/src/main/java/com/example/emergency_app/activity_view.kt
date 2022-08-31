package com.example.emergency_app

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class activity_view : AppCompatActivity() {
    lateinit var ListUsers: ListView
    lateinit var adapter: Customadapter
    lateinit var users: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)


        ListUsers= findViewById(R.id.Users_list)
        users = ArrayList()
        adapter = Customadapter(this,users)
        //CONNECT TO THE USERS TABLE/CHILD TO FETCH DATA
        val reference = FirebaseDatabase.getInstance().getReference().child("Users")
        //START FETCHING THE DATA
        reference.addValueEventListener(object : ValueEventListener{
            //OVERRIDE THE ON DATA CHANGE
            override fun onDataChange(snapshot: DataSnapshot) {
                users.clear()
                //USE FOR LOOP TO ADD THE USERS ON THE ARRAY LIST
                for (snap in snapshot.children){
                    val user = snap.getValue(User::class.java)
                    users.add(user !!)
                }
                adapter.notifyDataSetChanged()
            }
            //OVERRIDE ON CANCELLED METHOD
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"Please contact the admin",Toast.LENGTH_LONG).show()
            }
        })
        ListUsers.adapter = adapter
        //SET AN ON ITEM CLICK LISTENER TO THE LISTVIEW
        ListUsers.setOnItemClickListener { adapterView, view, i, l ->
            val userid = users.get(i).id
            val deletion_reference = FirebaseDatabase.getInstance().getReference().child("Users/$userid")
            //SET AN ALERT WHEN SOMEONE CLICKS ON THE ITEM
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("ALERT!!")
            alertDialog.setMessage("Select an option you want to perform")
            alertDialog.setNegativeButton("Update",DialogInterface.OnClickListener { dialogInterface, i ->

            })
            alertDialog.setPositiveButton("Delete",DialogInterface.OnClickListener { dialogInterface, i ->
                reference.removeValue()
                Toast.makeText(applicationContext,"Deleted Successfully",Toast.LENGTH_LONG).show()
            })
        }

    }
}