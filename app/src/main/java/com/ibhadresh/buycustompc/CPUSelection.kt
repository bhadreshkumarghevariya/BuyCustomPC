package com.ibhadresh.buycustompc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class CPUSelection : AppCompatActivity() {
    private lateinit var processorRecyclerView: RecyclerView
    private lateinit var processorArrayList: ArrayList<CPU>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cpuselection)

        processorRecyclerView = findViewById(R.id.processorRecyclerView)
        processorRecyclerView.layoutManager = LinearLayoutManager(this)
        processorRecyclerView.setHasFixedSize(true)

        processorArrayList = arrayListOf<CPU>()


        val docName:String = intent.getStringExtra("docRef").toString()
        val socketType:String = intent.getStringExtra("socketType").toString()
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
                processorRecyclerView.adapter = CPUAdapter(processorArrayList)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }





    }
}