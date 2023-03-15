package com.ibhadresh.buycustompc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class MotherBoardSelection : AppCompatActivity(),MotherBoardAdapter.OnItemClickListner {
    private lateinit var dbref : DatabaseReference
    private lateinit var motherBoardRecyclerView: RecyclerView
    private lateinit var motherBoardArrayList: ArrayList<MotherBoard>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mother_board_selection)

        motherBoardRecyclerView = findViewById(R.id.motherBoardRecyclerView)
        motherBoardRecyclerView.layoutManager = LinearLayoutManager(this)
        motherBoardRecyclerView.setHasFixedSize(true)

        motherBoardArrayList = arrayListOf<MotherBoard>()
        getMotherBoardData()
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
        val clickedItem:MotherBoard = motherBoardArrayList[position]
//        Log.d("TAGG",clickedItem.toString())



        val intent = Intent(this,CPUSelection::class.java)
        startActivity(intent)
    }

    private fun getMotherBoardData() {
        val db = Firebase.firestore
        db.collection("motherboards").get().addOnSuccessListener {
            result -> for(document in result){
//                Log.d("MBDOC","${document.id} => ${document.data}")
                motherBoardArrayList.add(document.toObject<MotherBoard>())
//            Log.d("MBLIST",motherBoardArrayList.toString())
            }
            motherBoardRecyclerView.adapter = MotherBoardAdapter(motherBoardArrayList,this)

        }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }

    }
}