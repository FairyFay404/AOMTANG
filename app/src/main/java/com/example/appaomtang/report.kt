package com.example.appaomtang

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [report.newInstance] factory method to
 * create an instance of this fragment.
 */
class report : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var payList:ArrayList<Entry>
    lateinit var yearsList:ArrayList<String>

//    private fun getList(): ArrayList<Entry> {
//        payList.add(Entry(0F,60.0F))
//        payList.add(Entry(1F,100.0F))
//        payList.add(Entry(2F,50.0F))
//        payList.add(Entry(3F,30.0F))
//        payList.add(Entry(4F,80.0F))
//        return payList
//    }
//    private fun getYears():ArrayList<String>{
//        yearsList.add("2017")
//        yearsList.add("2018")
//        yearsList.add("2019")
//        yearsList.add("2020")
//        yearsList.add("2021")
//        return yearsList
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val NoOfEmp = ArrayList<PieEntry>()
        val pieChart = view.findViewById<PieChart>(R.id.pieChart)
        NoOfEmp.add(PieEntry(10f,"2017"))
        NoOfEmp.add(PieEntry(20f, "2018"))
        NoOfEmp.add(PieEntry(30f, "2019"))
        NoOfEmp.add(PieEntry(40f, "2020"))
        NoOfEmp.add(PieEntry(50f, "2021"))
        val dataSet = PieDataSet(NoOfEmp, "No. of students")
        val data =  PieData(dataSet)
        pieChart.setData(data)

        val Pastel_Colors = listOf<Int>(Color.rgb(64, 89, 128), Color.rgb(149, 165, 124), Color.rgb(217, 184, 162) ,Color.rgb(191, 134, 134), Color.rgb(179, 48, 80) )
        dataSet.setColors(Pastel_Colors); //เปลี่ยนสีจากข้างบนนะ
        pieChart.animateXY(500, 500); //ปรับความไวตรงนี้ได้นะ
//        payList=ArrayList()
//        yearsList=ArrayList()
//        val pieDataSet =PieDataSet(getList(view),"No. of students")
//        val pieData =PieData(getYears(view),pieDataSet)
//        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS)
//        var pieChart:PieChart=view.findViewById(R.id.pieChart)
//        pieChart.data=pieData
        //chart.absoluteAngles

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
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment report.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                report().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}