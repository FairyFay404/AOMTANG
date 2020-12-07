package com.example.appaomtang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Member

class MainFragment: Fragment() {
    var recyclerView: RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberList = getDataFromJson()

        val memberAdapter = MemberAdapter(memberList, this)

        recyclerView?.apply {
            recyclerView!!.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL,
                    false)
            isNestedScrollingEnabled = false
            adapter = memberAdapter
            onFlingListener = null
        }

        memberAdapter.notifyDataSetChanged()

    }

    private fun getDataFromJson(): ArrayList<Member> {
        val inputStream = requireActivity().assets.open("google-services.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, charset("UTF-8"))
        val listType = object : TypeToken<ListMemberItem>() {}.type
        val gson = Gson().fromJson<ListMemberItem>(json, listType)
        return gson.members
    }

}
