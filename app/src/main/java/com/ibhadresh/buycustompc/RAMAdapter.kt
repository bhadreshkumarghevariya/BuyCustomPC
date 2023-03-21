package com.ibhadresh.buycustompc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RAMAdapter(
    private val context: Context,
    private val ramList: ArrayList<RAM>,
    private val listener : OnItemClickListner
    ):
    RecyclerView.Adapter<RAMAdapter.RAMViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RAMViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ram_item,parent,false)
        return RAMViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ramList.size
    }

    override fun onBindViewHolder(holder: RAMViewHolder, position: Int){
        var currentRAM = ramList[position]
        holder.ramName.text = currentRAM.ramName
        holder.ramSpeed.text = currentRAM.ramSpeed
        holder.ramType.text = currentRAM.ramType
        holder.price.text = currentRAM.price.toString()

        Glide.with(context)
            .load(currentRAM.imgURI)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imgURI)
    }

    inner class RAMViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener  {
        val ramName:TextView = itemView.findViewById(R.id.tvRamProductName)
        val ramType:TextView = itemView.findViewById(R.id.tvRamType)
        val ramSpeed:TextView = itemView.findViewById(R.id.tvRamSpeed)
        val imgURI:ImageView = itemView.findViewById(R.id.ramImageView)
        val price : TextView = itemView.findViewById(R.id.tvRamPrice)

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