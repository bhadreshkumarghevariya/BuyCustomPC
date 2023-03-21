package com.ibhadresh.buycustompc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class CPUDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cpudetails)

        val docName = intent.getStringExtra("docRef").toString()
        val ramType = intent.getStringExtra("ramType").toString()
        val motherboardName = intent.getStringExtra("motherboardName").toString()
        val processorName = intent.getStringExtra("processorName").toString()

        val mbPrice = intent.getDoubleExtra("mbPrice",0.0)
        val cpuPrice = intent.getDoubleExtra("cpuPrice",0.0)
        val imgURI = intent.getStringExtra("imgURI")

//        intent.putExtra("cpuSocketType",processorArrayList[position].socketType)
//        intent.putExtra("description",processorArrayList[position].description)

        val cpuSocketType = intent.getStringExtra("cpuSocketType").toString()
        val description = intent.getStringExtra("description").toString()


        val tvDetailsCPUDescription : TextView = findViewById(R.id.tvDetailsCPUDescription)
        val tvDetailsCPUSocketType : TextView = findViewById(R.id.tvDetailsCPUSocketType)
        val tvDetailsCPUPrice :TextView = findViewById(R.id.tvDetailsCPUPrice)
        val tvCPUDetailsProductName : TextView = findViewById(R.id.tvCPUDetailsProductName)
        val cpuImageView : ImageView = findViewById(R.id.cpuDetailsImage)
        val btnSelectCPU = findViewById<Button>(R.id.btnSelectCPU)

        Glide.with(this)
            .load(imgURI)
            .placeholder(R.drawable.ic_launcher_background)
            .into(cpuImageView)

        tvCPUDetailsProductName.text = processorName
        tvDetailsCPUPrice.text = cpuPrice.toString()
        tvDetailsCPUSocketType.text = cpuSocketType
        tvDetailsCPUDescription.text = description

        btnSelectCPU.setOnClickListener {
            val intent = Intent(this,RAMSelection::class.java)
            intent.putExtra("docRef", docName)
            intent.putExtra("ramType",ramType)
            intent.putExtra("motherboardName",motherboardName)
            intent.putExtra("processorName",processorName)
            intent.putExtra("cpuPrice",cpuPrice)
            intent.putExtra("mbPrice",mbPrice)
            startActivity(intent)
        }


    }
}