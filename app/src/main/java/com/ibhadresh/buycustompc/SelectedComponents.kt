package com.ibhadresh.buycustompc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class SelectedComponents : AppCompatActivity() {
    lateinit var motherboardName:String
    lateinit var processorName:String
    lateinit var ramName:String
    lateinit var docName:String
//    lateinit var mb:MotherBoard
//    lateinit var processor:CPU
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
        val tvSelectedRamSpeed :TextView = findViewById(R.id.tvSelectedRamSpeed)
        val tvSelectedRamType :TextView = findViewById(R.id.tvSelectedRamType)
        val tvSelectedSocketType :TextView = findViewById(R.id.tvSelectedSocketType)
        val tvSelectedRamProductName :TextView = findViewById(R.id.tvSelectedRamProductName)
        val tvSelectedMotherBoardProductName :TextView = findViewById(R.id.tvSelectedMotherBoardProductName)
        val tvSelectedMotherBoardDescription :TextView = findViewById(R.id.tvSelectedMotherBoardDescription)
        val tvSelectedCPUSocketType :TextView = findViewById(R.id.tvSelectedCPUSocketType)

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
                    Log.d("ram",ram.toString())
                }
            }











    }
}