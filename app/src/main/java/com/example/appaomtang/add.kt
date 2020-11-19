package com.example.appaomtang

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.provider.Telephony
import android.text.format.DateFormat.is24HourFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.DateFormat
import java.util.*
import java.util.zip.Inflater
import javax.xml.datatype.DatatypeConstants.MONTHS

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [add.newInstance] factory method to
 * create an instance of this fragment.
 */


class add : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



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

        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonDate = view.findViewById<Button>(R.id.date_button)


        buttonDate.setOnClickListener{
            dateClicked(view) //it = view (view ตัวมันเองอะ เก็ทมั้ย view ของ button) เราส่ง view ไปให้ฟังก์ชัน dateClicked
        }

    }


    private fun dateClicked(view:View){
        val textDate = view.findViewById<TextView>(R.id.date_textview)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            textDate.text = "วันที่ " + dayOfMonth + "/" + monthOfYear+ "/" + year
            Toast.makeText(view.context,dayOfMonth.toString(),Toast.LENGTH_LONG).show()
        }, year, month+1, day)

        dpd.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment add.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            add().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}