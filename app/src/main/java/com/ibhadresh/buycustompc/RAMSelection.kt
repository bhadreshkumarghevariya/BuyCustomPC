package com.ibhadresh.buycustompc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class RAMSelection : AppCompatActivity(), RAMAdapter.OnItemClickListner {
    lateinit var docName:String
    lateinit var ramType:String
    lateinit var motherboardName:String
    lateinit var processorName:String
    private lateinit var ramRecyclerView: RecyclerView
    private lateinit var ramList:ArrayList<RAM>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ramselection)

        ramRecyclerView = findViewById(R.id.ramRecyclerView)
        ramRecyclerView.layoutManager = LinearLayoutManager(this)
        ramRecyclerView.setHasFixedSize(true)

        motherboardName = intent.getStringExtra("motherboardName").toString()
        processorName = intent.getStringExtra("processorName").toString()

        ramList = arrayListOf<RAM>()

        docName = intent.getStringExtra("docRef").toString()
        ramType = intent.getStringExtra("ramType").toString()

        val db = Firebase.firestore
        val docRef = db.collection("ram")

        docRef.whereEqualTo("ramType",ramType)
            .get()
            .addOnSuccessListener {
                    documents ->
                for (document in documents){
                    ramList.add(document.toObject<RAM>())
                }
                ramRecyclerView.adapter = RAMAdapter(ramList,this)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }



    }

    override fun onItemClick(position: Int) {
        val data = hashMapOf(
            "ramName" to ramList[position].ramName
        )
        val db = Firebase.firestore

        db.collection("currentBuildsWithUserId")
            .document(docName)
            .set(data, SetOptions.merge())
            .addOnSuccessListener {
                val intent = Intent(this,SelectedComponents::class.java)
                intent.putExtra("docRef", docName)
                intent.putExtra("motherboardName",motherboardName)
                intent.putExtra("processorName",processorName)
                intent.putExtra("ramName",ramList[position].ramName)
                startActivity(intent)
                Log.d("TAG", "DocumentSnapshot written with ID: ${docName}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }

    }


}