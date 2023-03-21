package com.ibhadresh.buycustompc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class SelectedComponents : AppCompatActivity() {
    lateinit var motherboardName:String
    lateinit var processorName:String
    lateinit var ramName:String
    lateinit var docName:String
    var totalPrice :Double = 0.0
    lateinit var ram:RAM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_components)

        motherboardName = intent.getStringExtra("motherboardName").toString()
        processorName = intent.getStringExtra("processorName").toString()
        ramName = intent.getStringExtra("ramName").toString()

        docName = intent.getStringExtra("docRef").toString()


        val tvSelectedCPUDescription :TextView = findViewById(R.id.tvSelectedCPUDescription)
        val tvSelectedCPUProductName :TextView = findViewById(R.id.tvSelectedCPUProductName)
        val cpuSelectedImageView:ImageView = findViewById(R.id.cpuSelectedImageView)
        val mbSelectedImageView : ImageView = findViewById(R.id.mbSelectedImageView)
        val ramSelectedImageView : ImageView = findViewById(R.id.ramSelectedImageView)

        val tvSelectedRamSpeed :TextView = findViewById(R.id.tvSelectedRamSpeed)
        val tvSelectedRamType :TextView = findViewById(R.id.tvSelectedRamType)
        val tvSelectedSocketType :TextView = findViewById(R.id.tvSelectedSocketType)
        val tvSelectedRamProductName :TextView = findViewById(R.id.tvSelectedRamProductName)
        val tvSelectedMotherBoardProductName :TextView = findViewById(R.id.tvSelectedMotherBoardProductName)
        val tvSelectedMotherBoardDescription :TextView = findViewById(R.id.tvSelectedMotherBoardDescription)
        val tvSelectedCPUSocketType :TextView = findViewById(R.id.tvSelectedCPUSocketType)
        val tvSelectedTotalPrice : TextView = findViewById(R.id.tvSelectedTotalPrice)
        val tvSelectedRamPrice : TextView = findViewById(R.id.tvSelectedRamPrice)
        val tvSelectedMBPrice : TextView = findViewById(R.id.tvSelectedMBPrice)
        val tvSelectedCPUPrice : TextView = findViewById(R.id.tvSelectedCPUPrice)

        val btnPrcToCktOut : Button = findViewById(R.id.btnPrcToCktout)

        btnPrcToCktOut.setOnClickListener(){
            val intent = Intent(this,CheckOut::class.java)
            intent.putExtra("motherboardName",motherboardName)
            intent.putExtra("processorName",processorName)
            intent.putExtra("ramName",ramName)
            intent.putExtra("totalPrice",totalPrice)
            startActivity(intent)
        }


        val db = Firebase.firestore

        val docRef0 = db.collection("motherboards")

        docRef0.whereEqualTo("productName",motherboardName)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("MotherBoard", document.toString())
                    val mb = document.toObject<MotherBoard>()
                    tvSelectedMotherBoardProductName.text = mb.productName
                    tvSelectedMotherBoardDescription.text = mb.description
                    tvSelectedSocketType.text = mb.socketType
                    tvSelectedMBPrice.text = mb.price.toString()
                    totalPrice = totalPrice + mb.price!!
                    tvSelectedTotalPrice.text = "$ "+ totalPrice.toString()
                    Glide.with(this)
                        .load(mb.imgURI)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(mbSelectedImageView)
                    Log.d("MotherBoard",mb.toString())
                }
            }

        val docRef1 = db.collection("processors")

        docRef1.whereEqualTo("processorName",processorName)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("Processor", document.toString())
                    val processor = document.toObject<CPU>()
                    tvSelectedCPUProductName.text = processor.processorName
                    tvSelectedCPUDescription.text = processor.description
                    tvSelectedCPUSocketType.text = processor.socketType
                    tvSelectedCPUPrice.text = processor.cpuPrice.toString()
                    totalPrice=totalPrice+processor.cpuPrice!!
                    tvSelectedTotalPrice.text = "$ "+ totalPrice.toString()
                    Glide.with(this)
                        .load(processor.imgURI)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(cpuSelectedImageView)
                    Log.d("Processor",processor.toString())
                }
            }

        val docRef = db.collection("ram")

        docRef.whereEqualTo("ramName",ramName)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("ram", document.toString())
                    val ram = document.toObject<RAM>()
                    tvSelectedRamProductName.text = ram.ramName
                    tvSelectedRamSpeed.text = ram.ramSpeed
                    tvSelectedRamType.text = ram.ramType
                    tvSelectedRamPrice.text = ram.price.toString()

                    totalPrice = totalPrice + ram.price!!
                    tvSelectedTotalPrice.text = "$ "+ totalPrice.toString()
                    Glide.with(this)
                        .load(ram.imgURI)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(ramSelectedImageView)
                    Log.d("ram",ram.toString())
                }
            }
    }
}