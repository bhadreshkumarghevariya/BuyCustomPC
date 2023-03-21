package com.ibhadresh.buycustompc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class CPUAdapter (
    private val context: Context,
    private val processorList : ArrayList<CPU>,
    private val listener :OnItemClickListner
    ):
    RecyclerView.Adapter<CPUAdapter.CPUViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CPUViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cpu_item,parent,false)
        return  CPUViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return processorList.size
    }

    override fun onBindViewHolder(holder: CPUViewHolder, position: Int) {
        val currentProcessor = processorList[position]
        holder.processorName.text = currentProcessor.processorName
        holder.socketType.text = currentProcessor.socketType
        holder.description.text = currentProcessor.description
        holder.cpuPrice.text = currentProcessor.cpuPrice.toString()


        Glide.with(context)
            .load(currentProcessor.imgURI)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imgURI)
    }

    inner class CPUViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val processorName:TextView = itemView.findViewById(R.id.tvCPUProductName)
        val socketType:TextView = itemView.findViewById(R.id.tvCPUSocketType)
        val description:TextView = itemView.findViewById(R.id.tvCPUDescription)
        val imgURI : ImageView = itemView.findViewById(R.id.cpuImageView)
        val cpuPrice : TextView = itemView.findViewById(R.id.tvCPUPrice)

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