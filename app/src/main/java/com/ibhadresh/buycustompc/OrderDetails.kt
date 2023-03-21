package com.ibhadresh.buycustompc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class OrderDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val tvMBOrderProductName:TextView = findViewById(R.id.tvMBOrderProductName)
        val tvCPUOrderProductName:TextView = findViewById(R.id.tvCPUOrderProductName)
        val tvRamOrderProductName:TextView = findViewById(R.id.tvRamOrderProductName)
        val tvOrderUserName:TextView = findViewById(R.id.tvOrderUserName)
        val tvOrderUserAddress:TextView = findViewById(R.id.tvOrderUserAddress)
        val tvOrderTotal:TextView = findViewById(R.id.tvOrderTotal)



        tvMBOrderProductName.text = intent.getStringExtra("motherboardName")
        tvCPUOrderProductName.text = intent.getStringExtra("processorName")
        tvRamOrderProductName.text = intent.getStringExtra("ramName")
        tvOrderTotal.text = intent.getDoubleExtra("total",0.0).toString()
        tvOrderUserName.text = intent.getStringExtra("name")
        tvOrderUserAddress.text = intent.getStringExtra("address")



    }
}