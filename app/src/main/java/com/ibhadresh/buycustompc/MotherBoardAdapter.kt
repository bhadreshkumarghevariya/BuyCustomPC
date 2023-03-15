package com.ibhadresh.buycustompc

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MotherBoardAdapter(
    private val motherBoardList : ArrayList<MotherBoard>,
    private val listener : OnItemClickListner
    ):RecyclerView.Adapter<MotherBoardAdapter.MotherBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotherBoardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.mother_board_item,parent,false)
//        itemView.setOnClickListener()
        return MotherBoardViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return motherBoardList.size
    }

    override fun onBindViewHolder(holder: MotherBoardViewHolder, position: Int) {
        val currentMotherBoard = motherBoardList[position]
        holder.productName.text = currentMotherBoard.productName
        holder.socketType.text = currentMotherBoard.socketType
        holder.description.text = currentMotherBoard.description

//        holder.itemView.setOnClickListener{
//            Log.d("ON CLICK",currentMotherBoard.socketType.toString())
//            val data = hashMapOf(
//                "userId" to "user1",
//                "motherBoardName" to currentMotherBoard.productName,
//                "cpuSocketType" to currentMotherBoard.socketType
//            )
//
//            val db = Firebase.firestore
//            db.collection("currentBuildsWithUserId")
//                .add(data)
//                .addOnSuccessListener { documentReference ->
//                    Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
//                }
//
//                .addOnFailureListener { e ->
//                    Log.w("TAG", "Error adding document", e)
//                }
//
//        }

    }

    inner class MotherBoardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        val productName : TextView = itemView.findViewById(R.id.tvMotherBoardProductName)
        val socketType : TextView = itemView.findViewById(R.id.tvSocketType)
        val description : TextView =itemView.findViewById(R.id.tvMotherBoardDescription)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position= adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListner{
        fun onItemClick(position: Int)
    }
}