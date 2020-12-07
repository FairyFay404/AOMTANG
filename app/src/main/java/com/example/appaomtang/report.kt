
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
import com.github.mikephil.charting.formatter.PercentFormatter


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

//    lateinit var payList:ArrayList<Entry>
    //   lateinit var yearsList:ArrayList<String>

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
        NoOfEmp.add(PieEntry(100f, "เงินเดือน"))
        NoOfEmp.add(PieEntry(20f, "OT"))
        NoOfEmp.add(PieEntry(30f, "โบนัส"))
        NoOfEmp.add(PieEntry(40f, "Income"))
        NoOfEmp.add(PieEntry(50f, "ดอกเบี้ย"))
        val dataSet = PieDataSet(NoOfEmp, "ประเภทของรายรับ")
        val data =  PieData(dataSet)
        pieChart.setData(data)
        pieChart.setCenterText("รายรับ");
        pieChart.setCenterTextSize(12F);
        pieChart.setHoleRadius(40F);
        pieChart.setTransparentCircleRadius(50F);
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1Length(0.5f);
        dataSet.setValueLinePart2Length(0.5f);
        dataSet.setValueTextSize(14F);
        dataSet.setSelectionShift(10F);
        pieChart.setUsePercentValues(true);
        dataSet.setValueFormatter(PercentFormatter())
        val Pastel_Colors = listOf<Int>(Color.rgb(64, 89, 128), Color.rgb(149, 165, 124), Color.rgb(217, 184, 162), Color.rgb(191, 134, 134), Color.rgb(179, 48, 80))
        dataSet.setColors(Pastel_Colors); //เปลี่ยนสีจากข้างบนนะ
        pieChart.animateXY(500, 500); //ปรับความไวตรงนี้ได้นะ


        val NoOfEmp2 = ArrayList<PieEntry>()
        val pieChart2 = view.findViewById<PieChart>(R.id.pieChart2)
        NoOfEmp2.add(PieEntry(100f, "ค่าอาหาร"))
        NoOfEmp2.add(PieEntry(200f, "ค่าที่พัก"))
        NoOfEmp2.add(PieEntry(80f, "ค่าเดินทาง"))
        NoOfEmp2.add(PieEntry(140f, "ค่าช๊อปปิ้ง"))
        NoOfEmp2.add(PieEntry(160f, "ค่าบำรุง"))
        val dataSet2 = PieDataSet(NoOfEmp2, "ประเภทของรายจ่าย")
        val data2 =  PieData(dataSet2)
        pieChart2.setData(data2)
        pieChart2.setCenterText("รายจ่าย");
        pieChart2.setCenterTextSize(12F);
        pieChart2.setHoleRadius(40F);
        pieChart2.setTransparentCircleRadius(50F);
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet2.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet2.setValueLinePart1Length(0.5f);
        dataSet2.setValueLinePart2Length(0.5f);
        dataSet2.setValueTextSize(14F);
        dataSet2.setSelectionShift(10F);
        pieChart2.setUsePercentValues(true);
        dataSet2.setValueFormatter(PercentFormatter())
        val Pastel_Colors2 = listOf<Int>(Color.rgb(64, 89, 128), Color.rgb(149, 165, 124), Color.rgb(217, 184, 162), Color.rgb(191, 134, 134), Color.rgb(179, 48, 80))
        dataSet2.setColors(Pastel_Colors2); //เปลี่ยนสีจากข้างบนนะ
        pieChart2.animateXY(500, 500); //ปรับความไวตรงนี้ได้นะ



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