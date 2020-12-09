package com.example.appaomtang

import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class NoteRecycleAdapter(var noteList:ArrayList<Note_data>) :RecyclerView.Adapter<NoteRecycleAdapter.NoteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.normallist,parent,false)
        return NoteViewHolder(itemView)
    }
    override fun getItemCount(): Int {

        return noteList.size
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = noteList[position]
        holder.title.text =current.title
        holder.desription.text=current.description
        holder.textviewcheat.text=current.time
        holder.delbuttom.setOnClickListener {
            holder.title.text="delete"
            db.collection("note123")
                    .whereEqualTo("time",current.time)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            holder.title.text=document.id
                            val datatodel =holder.title.text.toString()
                            deletedata(datatodel)
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w("MOO", "Error getting documents: ", exception)
                    }
            holder.desription.text="delete complete please reflesh"

        }
    }


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.texttitle)
        var desription = itemView.findViewById<TextView>(R.id.textdesription)
        var delbuttom=itemView.findViewById<ImageButton>(R.id.imagedelButton)
        var textviewcheat=itemView.findViewById<TextView>(R.id.textViewcheat)

    }
    fun deletedata(doc:String){
        db.collection("note123").document(doc)
                .delete()
                .addOnSuccessListener {

                }
                .addOnFailureListener{

                }
    }

}
