package com.example.appaomtang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Member

class MemberAdapter(private val items: ArrayList<Member>,
                    private val listener: MainFragment): RecyclerView.Adapter<MemberAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_note, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { listener }
    }

    class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        var recyclerView: RecyclerView? = null

        fun bind(member: Member) {
            itemView.apply {
                recyclerView?.adapter = NoteRecycleAdapter(list)
              //  recyclerView?.layoutManager = LinearLayoutManager(activity)
            }
        }
    }

}
