package com.example.appaomtang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NormalRecycleAdapter(var normalList:ArrayList<normal_data>) : RecyclerView.Adapter<NormalRecycleAdapter.NormalViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.normalpaylist,parent,false)
        return NormalViewHolder(itemView)
    }
    override fun getItemCount(): Int {

        return normalList.size
    }
    override fun onBindViewHolder(holder: NormalViewHolder, position: Int) {
        val current:normal_data= normalList[position]
        holder.money.text= current.money//.toString()
        holder.typepay.text=current.typepay
        holder.notewrite.text =current.notewrite
        holder.dateselect.text =current.dateselect

    }


    class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var money = itemView.findViewById<TextView>(R.id.textmoney)
        var typepay = itemView.findViewById<TextView>(R.id.textViewtype)
        var notewrite = itemView.findViewById<TextView>(R.id.textViewdesr)
        var dateselect = itemView.findViewById<TextView>(R.id.textViewdate)


    }


}
