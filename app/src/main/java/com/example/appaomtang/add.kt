package com.example.appaomtang

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.provider.Telephony
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*
import java.util.zip.Inflater
import kotlin.time.measureTimedValue

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var textdatesort=view.findViewById<TextView>(R.id.textdatesort)
        var tcn=view.findViewById<TextView>(R.id.textcheatnum)
        var moneyedit =view.findViewById<EditText>(R.id.number_money)
        var noteedit=view.findViewById<EditText>(R.id.note_edit)
        var buttonDate =view.findViewById<Button>(R.id.date_textview)
        var spin=view.findViewById<Spinner>(R.id.spinner2)
        var textspin =view.findViewById<TextView>(R.id.textspin)
        val activitiesList= arrayListOf<String>("My Wallet","ใช้จ่ายทั่วไป ","เพื่อการศึกษา","เงินออมฉุกเฉิน")
        val arrayAdapter=ArrayAdapter(view.context,android.R.layout.preference_category,activitiesList)
                .also {
                    adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
                    var spin:Spinner=view.findViewById(R.id.spinner2)
                    spin.adapter=adapter
                }

            spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val text = "${activitiesList[position]}"
                    if (view != null) {
                        //Toast.makeText(view.context, "select ${activitiesList[position]}", Toast.LENGTH_SHORT).show()

                        textspin.text="${activitiesList[position]}"

                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }


        buttonDate.setOnClickListener{
            dateClicked(view)
        }
        var buttontype=view.findViewById<Button>(R.id.buttontype)
        buttontype.setOnClickListener {
            typepick(view)
//            readnumcal(view,buttontype.text.toString())
        }

        var buttonok=view.findViewById<Button>(R.id.buttonok)
        buttonok.setOnClickListener {
            Toast.makeText(view.context,"บันทึกสำเร็จ",Toast.LENGTH_SHORT).show()
//            readnumcal(view,buttontype.text.toString())
            val wallet=textspin.text.toString()
            val type =buttontype.text.toString()
            val date = buttonDate.text.toString()
            val date2=textdatesort.text.toString()
            val money1 =moneyedit.text.toString()
            val note =noteedit.text.toString()
            val num2=tcn.text.toString()

//            var money11=money1.toDouble()
//            var num22=num2.toDouble()
//            var money1111=num22+money11

//            updatedata(view,money1111,buttontype.text.toString())
            saveFireStore(view,money1, note, type, date,wallet,date2)
//            readnumcal(view,buttontype.text.toString())
        }


    }

    fun updatedata(view: View,num11: Double,typ:String){
        val dbnum=db.collection("normal").document("normal")
        dbnum.update(typ,num11.toString())
    }
    fun updatedata2(view: View,num11: Double){
        val dbnum=db.collection("normal").document("normal")
        dbnum.update("OT",num11.toString())
    }
        fun readnumcal(view: View,typ:String){
            var tcn=view.findViewById<TextView>(R.id.textcheatnum)
            val dbnum = db.collection("normal").document("normal")
            dbnum.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            tcn.text = document[typ].toString()
                        }
                    }
        }

    fun saveFireStore(view: View,money :String,note : String,type:String,date:String,wallet:String,date2:String){

        val t=Calendar.getInstance()
        val year=t.get(Calendar.YEAR).toDouble()
        val month=t.get(Calendar.MONTH).toDouble()
        val day=t.get(Calendar.DAY_OF_MONTH).toDouble()
        val hour=t.get(Calendar.HOUR_OF_DAY).toDouble()
        val minute=t.get(Calendar.MINUTE).toDouble()
        val sec=t.get(Calendar.SECOND).toDouble()
        val tim =year*31536000+month*2592000+day*86400+hour*3600+minute*60+sec
        val user :MutableMap<String,Any> = HashMap()
        user["time"] = tim.toString()
        user["money"] = money
        user["note"] = note
        user["type"] = type
        user["date"] = date
        user["wallet"] = wallet
        user["datesort"] =date2
        db.collection("add")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(view.context,"บันทึกสำเร็จ",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(view.context,"record Failed to add",Toast.LENGTH_SHORT).show()
                }
    }


    private fun dateClicked(view: View){
        val textDate=view.findViewById<Button>(R.id.date_textview)
        val textdatesort=view.findViewById<TextView>(R.id.textdatesort)
        val c=Calendar.getInstance()
        val year =c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day =c.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { view, years, monthOfYear, dayOfMonth ->
            val mo=monthOfYear+1
            val mcal=mo.toDouble()
            val ycal=years.toDouble()
            val dcal=dayOfMonth.toDouble()
            val cal=ycal*365+mcal*31+dcal
            textDate.text = "" + dayOfMonth + "/" + mo + "/" + years
            textdatesort.text=cal.toString()
            Toast.makeText(view.context,"เลือกสำเร็จ",Toast.LENGTH_SHORT).show()
        }, year, month, day)
        dpd.show()
    }

    @SuppressLint("ResourceAsColor")
    private fun typepick(view: View){
        val buttontype=view.findViewById<Button>(R.id.buttontype)
        val color_list= arrayOf("เงินเดือน","OT","โบนัส","income","ดอกเบี้ย","ค่าอาหาร","ค่าที่พัก","ค่าเดินทาง","ค่าช๊อปปิ้ง","ค่าบำรุง")
        val option_builder =AlertDialog.Builder(view.context).apply {
            setTitle("เลือกประเภทของธุรกรรม")
            setItems(color_list,{dialog,which->
                when(which){
                0->buttontype.text="เงินเดือน"
                1->buttontype.text="OT"
                2->buttontype.text="โบนัส"
                3->buttontype.text="income"
                4->buttontype.text="ดอกเบี้ย"
                5->buttontype.text="ค่าอาหาร"
                6->buttontype.text="ค่าที่พัก"
                7->buttontype.text="ค่าเดินทาง"
                8->buttontype.text="ค่าช๊อปปิ้ง"
                9->buttontype.text="ค่าบำรุง"
                }
            })

        }
        val alertDialog=option_builder.create()
        alertDialog.show()

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

        return inflater.inflate(R.layout.fragment_add, container, false)
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
