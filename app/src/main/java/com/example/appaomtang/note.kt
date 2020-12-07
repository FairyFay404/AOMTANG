package com.example.appaomtang

import android.content.ContentValues.TAG
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.reflect.Member

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var list = ArrayList<Note_data>()

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

        val db = FirebaseFirestore.getInstance()
       // setContenView(R.layout.fragment_note)
        super.onViewCreated(view, savedInstanceState)
        val titleText=view.findViewById<EditText>(R.id.edit_1)
        val DescText=view.findViewById<EditText>(R.id.edit_2)
        var buttoncreate=view.findViewById<Button>(R.id.buttonFragCreate)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_1)

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
           // val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_1)

            list.add(Note_data(titleText.text.toString(), DescText.text.toString()))
            recyclerView.adapter = NoteRecycleAdapter(list)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            //Toast.makeText(view.context,"บันทึกโน๊ตสำเร็จ"/*+titleText.text*/, Toast.LENGTH_SHORT).show()
        }
       // readFireStoreData()

        recyclerView.adapter = NoteRecycleAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter!!.notifyDataSetChanged()
       /* db.collection("Note")
                .get()
                .addOnCompleteListener{
                    val result:StringBuffer=StringBuffer()
                    if(it.isSuccessful){
                        for(document in it.result!!){
                            result.append(document.data.getValue("edit_1")).append(" ")
                                    .append(document.data.getValue("edit_2")) .append("\n\n")
                        }
                        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_1)
                        recyclerView.adapter = NoteRecycleAdapter(list)
                        recyclerView.layoutManager = LinearLayoutManager(activity)

                    }
                }*/
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

     fun saveFireStore(view: View,edit_1:String,edit_2: String){

         val db = FirebaseFirestore.getInstance()
         val user :MutableMap<String,Any> = HashMap()
             user["edit_1"] = edit_1
             user["edit_2"] = edit_2
             db.collection("Note")
             .add(user)
             .addOnSuccessListener {
                    Toast.makeText(view.context,"บันทึกสำเร็จ",Toast.LENGTH_SHORT).show()
              }
            .addOnFailureListener{
                   Toast.makeText(view.context,"record Failed to add",Toast.LENGTH_SHORT).show()
              }
    //readFireStoreData()
     }
     /*fun readFireStoreData(){
     val db = FirebaseFirestore.getInstance()
         db.collection("Note")
         .get()
         .addOnCompleteListener{
            val result:StringBuffer=StringBuffer()
            if(it.isSuccessful){
               for(document in it.result!!){
                 result.append(document.data.getValue("edit_1")).append(" ")
                         .append(document.data.getValue("edit_2")) .append("\n\n")
                 }
             }
         }
    }*/

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
    inner class CustomAdapter: RecyclerView.Adapter<CustomHolder>() {
        override fun onCreateViewHolder(parentView: ViewGroup, option: Int): CustomHolder {
           val view = LayoutInflater.from(context).inflate(R.layout.fragment_note,parentView,false)
            return CustomHolder(view)
        }

        override fun onBindViewHolder(holder: CustomHolder, position: Int){

        }
        var recyclerView: RecyclerView? = null
        override fun getItemCount(): Int {
            return 20
        }

    }
    val titleText= view?.findViewById<EditText>(R.id.edit_1)
    val DescText= view?.findViewById<EditText>(R.id.edit_2)
    inner class CustomHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title = titleText?.text.toString()
        val content = DescText?.text.toString()
    }
}

