package com.ibhadresh.buycustompc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class MotherBoardSelection : AppCompatActivity() {
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

    private fun getMotherBoardData() {
        val db = Firebase.firestore
        db.collection("motherboards").get().addOnSuccessListener {
            result -> for(document in result){
//                Log.d("MBDOC","${document.id} => ${document.data}")
                motherBoardArrayList.add(document.toObject<MotherBoard>())
//            Log.d("MBLIST",motherBoardArrayList.toString())
            }
            motherBoardRecyclerView.adapter = MotherBoardAdapter(motherBoardArrayList)
        }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }
//        val Query = FirebaseDatabase.getInstance().getReference("MotherBoards")
////        val Query = FirebaseDatabase.getInstance().getReference("MotherBoards").orderByChild("socketType").equalTo("LGA 1700")
//        Query.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists()){
//                    for(userSnapShot in snapshot.children){
//                        val motherBoard = userSnapShot.getValue(MotherBoard::class.java)
//                        motherBoardArrayList.add(motherBoard!!)
////                        Log.d("Hello","hello")
//                        Log.d("MBLIST2",motherBoardArrayList.toString())
//                    }
//                    motherBoardRecyclerView.adapter = MotherBoardAdapter(motherBoardArrayList)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        }
//        )
    }
}