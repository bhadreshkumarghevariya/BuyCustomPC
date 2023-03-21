package com.ibhadresh.buycustompc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CheckOut : AppCompatActivity() {
    lateinit var motherboardName:String
    lateinit var processorName:String
    lateinit var ramName:String
    var totalPrice : Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        motherboardName = intent.getStringExtra("motherboardName").toString()
        processorName = intent.getStringExtra("processorName").toString()
        ramName = intent.getStringExtra("ramName").toString()
        totalPrice = intent.getDoubleExtra("totalPrice",0.0)

        val checkout_total_amount : TextView = findViewById(R.id.checkout_total_amount)
        val checkout_name :EditText = findViewById(R.id.checkout_name)
        val checkout_email : EditText = findViewById(R.id.checkout_email)
        val checkout_address : EditText = findViewById(R.id.checkout_address)
        val checkout_zipcode : EditText = findViewById(R.id.checkout_zipcode)
        val checkout_card_number2 : EditText = findViewById(R.id.checkout_card_number2)
        val checkout_button : Button = findViewById(R.id.checkout_button)
        checkout_total_amount.text = totalPrice.toString()

        checkout_button.setOnClickListener(){
            val intent = Intent(this,OrderDetails::class.java)
            intent.putExtra("zipcode",checkout_zipcode.text)
            intent.putExtra("total amount",checkout_total_amount.text)
            intent.putExtra("name",checkout_name.text)
            intent.putExtra("email",checkout_email.text)
            intent.putExtra("address",checkout_address.text)
            intent.putExtra("contact number",checkout_card_number2.text)

            intent.putExtra("motherboardName",motherboardName)
            intent.putExtra("processorName",processorName)
            intent.putExtra("ramName",ramName)
            intent.putExtra("total",totalPrice)

            startActivity(intent)
        }
    }
}