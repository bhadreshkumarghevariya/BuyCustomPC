package com.ibhadresh.buycustompc

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MotherBoardAdapter(
    private val context: Context,
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
        holder.price.text = currentMotherBoard.price.toString()

        Glide.with(context)
            .load(currentMotherBoard.imgURI)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imgURI)
    }

    inner class MotherBoardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        val productName : TextView = itemView.findViewById(R.id.tvMotherBoardProductName)
        val socketType : TextView = itemView.findViewById(R.id.tvSocketType)
        val description : TextView =itemView.findViewById(R.id.tvMotherBoardDescription)
        var imgURI : ImageView = itemView.findViewById(R.id.mbImageView)
        var price : TextView = itemView.findViewById(R.id.tvMBPrice)

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