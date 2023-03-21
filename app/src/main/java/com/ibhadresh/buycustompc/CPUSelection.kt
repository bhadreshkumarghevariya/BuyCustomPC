package com.ibhadresh.buycustompc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class CPUSelection : AppCompatActivity(),CPUAdapter.OnItemClickListner {
    lateinit var docName:String
    lateinit var ramType:String
    lateinit var motherBoardName:String
    var mbPrice : Double = 0.0
    private lateinit var processorRecyclerView: RecyclerView
    private lateinit var processorArrayList: ArrayList<CPU>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cpuselection)

        title = "Processor"

        processorRecyclerView = findViewById(R.id.processorRecyclerView)
        processorRecyclerView.layoutManager = LinearLayoutManager(this)
        processorRecyclerView.setHasFixedSize(true)

        processorArrayList = arrayListOf<CPU>()

        motherBoardName = intent.getStringExtra("motherboardName").toString()

        mbPrice = intent.getDoubleExtra("mbPrice",0.0)

        Log.d("totalPrice from cpu",mbPrice.toString())


        docName = intent.getStringExtra("docRef").toString()
        val socketType:String = intent.getStringExtra("socketType").toString()
        ramType = intent.getStringExtra("ramType").toString()
        Log.d("SocketType",socketType)

        val db = Firebase.firestore
        val docRef = db.collection("processors")

        docRef.whereEqualTo("socketType",socketType)
            .get()
            .addOnSuccessListener {
                documents ->
                for (document in documents){
                    processorArrayList.add(document.toObject<CPU>())
                }
                processorRecyclerView.adapter = CPUAdapter(this,processorArrayList,this)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }
    }

    override fun onItemClick(position: Int) {
        val data = hashMapOf(
            "processorName" to processorArrayList[position].processorName
        )

        val db = Firebase.firestore

        db.collection("currentBuildsWithUserId")
            .document(docName)
            .set(data, SetOptions.merge())
            .addOnSuccessListener {
                val intent = Intent(this,CPUDetails::class.java)
                intent.putExtra("docRef", docName)
                intent.putExtra("ramType",ramType)
                intent.putExtra("motherboardName",motherBoardName)
                intent.putExtra("processorName",processorArrayList[position].processorName)
                intent.putExtra("cpuPrice",processorArrayList[position].cpuPrice)
                intent.putExtra("mbPrice",mbPrice)

                intent.putExtra("cpuSocketType",processorArrayList[position].socketType)
                intent.putExtra("description",processorArrayList[position].description)
                intent.putExtra("cpuName",processorArrayList[position].processorName)
                intent.putExtra("imgURI",processorArrayList[position].imgURI)

//                (processorArrayList[position].cpuPrice?.plus(totalPrice) ?: intent.putExtra("totalPrice",totalPrice)) as Double
                startActivity(intent)
                Log.d("TAG", "DocumentSnapshot written with ID: ${docName}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }
}