package com.example.appaomtang

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
        holder.money.text= current.money
        holder.typepay.text=current.typepay
        holder.notewrite.text =current.notewrite
        holder.dateselect.text =current.dateselect
        holder.textmoney.text=current.wallet
        holder.cheattext.text=current.time
        holder.delbuttom.setOnClickListener {
            db.collection("add")
                    .whereEqualTo("time",current.time)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            val datatodel =document.id
                            deletedata(datatodel)
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w("MOO", "Error getting documents: ", exception)
                    }
            holder.typepay.text="delete complete please reflesh"
            db.collection("normal").document("normal")
                    .get()
                    .addOnSuccessListener { document->
                        if (document != null){
                            holder.notewrite.text="${document["moneyin"]}"

                        }
                    }
                    .addOnFailureListener{

                    }
        }
    }


    class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var money = itemView.findViewById<TextView>(R.id.textmoney)
        var typepay = itemView.findViewById<TextView>(R.id.textViewtype)
        var notewrite = itemView.findViewById<TextView>(R.id.textViewdesr)
        var dateselect = itemView.findViewById<TextView>(R.id.textViewdate)
        var textmoney=itemView.findViewById<TextView>(R.id.textwallet)
        var delbuttom=itemView.findViewById<ImageButton>(R.id.imageButton)
        var cheattext=itemView.findViewById<TextView>(R.id.textViewcheat2)
    }

    fun deletedata(doc:String){
        db.collection("add").document(doc)
                .delete()
                .addOnSuccessListener {

                }
                .addOnFailureListener{

                }
    }

}
