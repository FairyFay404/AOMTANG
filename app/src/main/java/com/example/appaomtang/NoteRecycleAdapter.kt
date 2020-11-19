package com.example.appaomtang

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    }


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.texttitle)
        var desription = itemView.findViewById<TextView>(R.id.textdesription)

    }


}
