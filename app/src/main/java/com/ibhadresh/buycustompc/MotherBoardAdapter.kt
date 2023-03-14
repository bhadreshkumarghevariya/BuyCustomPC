package com.ibhadresh.buycustompc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MotherBoardAdapter(private val motherBoardList : ArrayList<MotherBoard>):RecyclerView.Adapter<MotherBoardAdapter.MotherBoardViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotherBoardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.mother_board_item,parent,false)
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
    }

    class MotherBoardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val productName : TextView = itemView.findViewById(R.id.tvMotherBoardProductName)
        val socketType : TextView = itemView.findViewById(R.id.tvSocketType)
        val description : TextView =itemView.findViewById(R.id.tvMotherBoardDescription)
    }
}