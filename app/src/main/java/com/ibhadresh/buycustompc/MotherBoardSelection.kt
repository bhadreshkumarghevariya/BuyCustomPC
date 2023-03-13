package com.ibhadresh.buycustompc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

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
        dbref = FirebaseDatabase.getInstance().getReference("MotherBoards")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapShot in snapshot.children){
                        val motherBoard = userSnapShot.getValue(MotherBoard::class.java)
                        motherBoardArrayList.add(motherBoard!!)
                    }
                    motherBoardRecyclerView.adapter = MotherBoardAdapter(motherBoardArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}