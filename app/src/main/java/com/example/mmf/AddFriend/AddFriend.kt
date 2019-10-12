package com.example.mmf.AddFriend

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mmf.DB
import com.example.mmf.R
import kotlinx.android.synthetic.main.add_friend.*
import java.lang.Exception

class AddFriend : AppCompatActivity() {

    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_friend)

        try {
            val bundle: Bundle = intent.extras!!
            id = bundle.getInt("ID",0)
            if (id!=0){
                edt_name.setText(bundle.getString("NameFriend"))
                edt_tel.setText(bundle.getString("TelFriend"))
                edt_Email.setText(bundle.getString("EmailFriend"))
            }
        }catch (ex: Exception){}

    }

    fun addStudent(view: View){
        val dbManager = DB(this)
        val values = ContentValues()
        values.put("Name", edt_name.text.toString())
        values.put("Tel", edt_tel.text.toString())
        values.put("Email", edt_Email.text.toString())

        if (id ==0){
            val ID = dbManager.insert(values)
            if (ID > 0){
                Toast.makeText(this, "Friend is added", Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Toast.makeText(this, "Error adding friend", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            val selectionArgs = arrayOf(id.toString())
            val ID = dbManager.update(values, "ID=?", selectionArgs)
            if (ID>0){
                Toast.makeText(this, "Friend is added", Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Toast.makeText(this, "Error adding friend", Toast.LENGTH_SHORT).show()
            }
        }
    }
}