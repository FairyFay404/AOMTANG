package com.example.appaomtang

import android.content.ContentValues.TAG
import android.graphics.Path
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.reflect.Member
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.nextTowards

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var list = ArrayList<Note_data>()
val db = FirebaseFirestore.getInstance()
val normalList=ArrayList<normal_data>()
val nuumcal=ArrayList<numcal>()
/**
 * A simple [Fragment] subclass.
 * Use the [note.newInstance] factory method to
 * create an instance of this fragment.
 */
class note : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleText=view.findViewById<EditText>(R.id.edit_1)
        val DescText=view.findViewById<EditText>(R.id.edit_2)
        var buttoncreate=view.findViewById<Button>(R.id.buttonFragCreate)
        readFireStore(view)
        buttoncreate.setOnClickListener {
            val title =titleText.text.toString()
            val content = DescText.text.toString()
            val builder=NotificationModel(view.context,title, content).createNotification()
            val notificationManagerCalling=NotificationManagerCalling(view.context,builder)
            notificationManagerCalling.createChannel()
            notificationManagerCalling.startNotification()

            val edit_1 =titleText.text.toString()
            val edit_2 = DescText.text.toString()
            saveFireStore(view,edit_1,edit_2)
            readFireStore(view)
        }
    }

    fun readFireStore(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_1)
        list.clear()
        db.collection("note123")
                .orderBy("time",Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        list.add(Note_data(document.data["edit_1"].toString(),document.data["edit_2"].toString(),document.data["time"].toString()))
                        recyclerView.adapter = NoteRecycleAdapter(list)
                        recyclerView.layoutManager = LinearLayoutManager(activity)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("MOO", "Error getting documents: ", exception)
                }
    }
     fun saveFireStore(view: View,edit_1:String,edit_2: String){

         val user :MutableMap<String,Any> = HashMap()
         val t=Calendar.getInstance()
         val year=t.get(Calendar.YEAR).toDouble()
         val month=t.get(Calendar.MONTH).toDouble()
         val day=t.get(Calendar.DAY_OF_MONTH).toDouble()
         val hour=t.get(Calendar.HOUR_OF_DAY).toDouble()
         val minute=t.get(Calendar.MINUTE).toDouble()
         val sec=t.get(Calendar.SECOND).toDouble()
         val tim =year*31536000+month*2592000+day*86400+hour*3600+minute*60+sec
             user["edit_1"] = edit_1
             user["edit_2"] = edit_2
             user["time"]= tim.toString()
             db.collection("note123")
             .add(user)
             .addOnSuccessListener {
                    Toast.makeText(view.context,"บันทึกสำเร็จ",Toast.LENGTH_SHORT).show()
              }
            .addOnFailureListener{
                   Toast.makeText(view.context,"record Failed to add",Toast.LENGTH_SHORT).show()
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
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment note.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            note().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


