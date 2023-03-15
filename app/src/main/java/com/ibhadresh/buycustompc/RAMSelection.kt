package com.ibhadresh.buycustompc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class RAMSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ramselection)

        val ramType:String = intent.getStringExtra("ramType").toString()

        val ramTypeTest = findViewById<TextView>(R.id.tvRamTypeTest)
//        ramTypeTest.text = ramType

        Log.d("Ram Type",ramType)
    }
}