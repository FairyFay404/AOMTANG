package com.example.appaomtang

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [normal.newInstance] factory method to
 * create an instance of this fragment.
 */
class normal : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activitiesList= arrayListOf<String>("My Wallet","ใช้จ่ายทั่วไป ","เพื่อการศึกษา","เงินออมฉุกเฉิน")
        val arrayAdapter=ArrayAdapter(view.context,android.R.layout.preference_category,activitiesList)
                .also {
                    adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
                    var spin:Spinner=view.findViewById(R.id.spinner)
                    spin.adapter=adapter
                }
        readFireStore(view)
        val b_in=view.findViewById<TextView>(R.id.b_in)
        val numeiei=10000
        //val numeiei=db.collection("normal").get()
        b_in.text=numeiei.toString()+" บาท"
    }
    fun readFireStore(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_2)
        normalList.clear()
        db.collection("add")
                .whereEqualTo("wallet", "My Wallet")
                //.orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        normalList.add(normal_data(document.data["money"].toString(),document.data["type"].toString(),document.data["note"].toString(),document.data["date"].toString(),document.data["wallet"].toString()))
                        recyclerView.adapter = NormalRecycleAdapter(normalList)
                        recyclerView.layoutManager = LinearLayoutManager(activity)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("MOO", "Error getting documents: ", exception)
                }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_normal, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment normal.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            normal().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}