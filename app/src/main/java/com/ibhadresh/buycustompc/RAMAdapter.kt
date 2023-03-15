package com.ibhadresh.buycustompc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RAMAdapter(
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
    }

    inner class RAMViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener  {
        val ramName:TextView = itemView.findViewById(R.id.tvRamProductName)
        val ramType:TextView = itemView.findViewById(R.id.tvRamType)
        val ramSpeed:TextView = itemView.findViewById(R.id.tvRamSpeed)

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