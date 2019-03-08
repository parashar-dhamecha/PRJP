package com.dxdevil.pd.prjp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_contacts.*

class Contacts : AppCompatActivity() {

    private var adapter:ContactsAdapter?=null
    private var recyclerView: RecyclerView? = null

    private var Con: ArrayList<String>?=null
    private var layoutManager: RecyclerView.LayoutManager? = null


    private val myImageList = intArrayOf(R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user)
    private val myImageNameList = arrayOf("Dhruv", "Yogita", "Parashar", "Rishabh", "p", "Harly", "Lamborghini", "Silver")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)


        layoutManager = LinearLayoutManager(this)



        recyclerView = findViewById(R.id.r1) as RecyclerView


        adapter = ContactsAdapter(this, Con!!)
        recyclerView!!.adapter = adapter

        adapter!!.notifyDataSetChanged()




    }



}