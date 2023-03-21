package com.ibhadresh.buycustompc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MotherBoardDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mother_board_details)

//        intent.putExtra("docRef", documentReference.id)
        val docRef = intent.getStringExtra("docRef")
//        intent.putExtra("socketType",motherBoardArrayList[position].socketType)
//        intent.putExtra("ramType",motherBoardArrayList[position].ramType)
//        intent.putExtra("motherboardName",motherBoardArrayList[position].productName)
//        intent.putExtra("mbPrice",motherBoardArrayList[position].price)
//
//        intent.putExtra("imgURI",motherBoardArrayList[position].imgURI)
//        intent.putExtra("descrpition",motherBoardArrayList[position].description)

        val socketType = intent.getStringExtra("socketType")
        val ramType = intent.getStringExtra("ramType")
        val productName = intent.getStringExtra("motherboardName")
        val price = intent.getDoubleExtra("mbPrice",0.0)
        val imgURI = intent.getStringExtra("imgURI")
        val description = intent.getStringExtra("description")

        Log.d("desc",description.toString())
        val tvDetailsMBPrice:TextView = findViewById(R.id.tvDetailsMBPrice)
        val tvDetailsMotherBoardDescription:TextView = findViewById(R.id.tvDetailsMotherBoardDescription)
        val tvDetailsSocketType:TextView = findViewById(R.id.tvDetailsSocketType)
        val tvMBDetailsProductName:TextView = findViewById(R.id.tvMBDetailsProductName)
        val mbDetailsImage : ImageView = findViewById(R.id.mbDetailsImage)
        val btnSelectMotherBoard = findViewById<Button>(R.id.btnSelectMotherBoard)

        Glide.with(this)
            .load(imgURI)
            .placeholder(R.drawable.ic_launcher_background)
            .into(mbDetailsImage)


        tvDetailsMBPrice.text = price.toString()
        tvDetailsSocketType.text = socketType
        tvDetailsMotherBoardDescription.text = description
        tvMBDetailsProductName.text = productName

        btnSelectMotherBoard.setOnClickListener{
            val intent = Intent(this,CPUSelection::class.java)
            intent.putExtra("docRef",docRef)
            intent.putExtra("socketType",socketType)
            intent.putExtra("ramType",ramType)
            intent.putExtra("motherboardName",productName)
            intent.putExtra("mbPrice",price)

            startActivity(intent)
        }



    }
}