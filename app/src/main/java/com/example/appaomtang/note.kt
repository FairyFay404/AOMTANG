package com.example.appaomtang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        super.onViewCreated(view, savedInstanceState)
        val titleText=view.findViewById<EditText>(R.id.edit_1)
        val DescText=view.findViewById<EditText>(R.id.edit_2)
        var buttoncreate=view.findViewById<Button>(R.id.buttonFragCreate)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_1)
        buttoncreate.setOnClickListener {
            val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_1)
            list.add(Note_data(titleText.text.toString(), DescText.text.toString()))
            recyclerView.adapter = NoteRecycleAdapter(list)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            Toast.makeText(view.context,"บันทึกโน๊ตสำเร็จ"/*+titleText.text*/, Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = NoteRecycleAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
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