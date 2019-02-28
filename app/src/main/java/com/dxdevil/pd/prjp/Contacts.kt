package com.dxdevil.pd.prjp

import android.os.Bundle
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.contactsadapter.view.*

class Contacts : AppCompatActivity() {

   private var adapter:ContactsAdapter?=null
    private var contactList: ArrayList<ModelClass>?=null
    private var layoutManager:RecyclerView.LayoutManager?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)




        contactList=ArrayList<ModelClass>()
        layoutManager=LinearLayoutManager(this)
        adapter=ContactsAdapter(contactList!!,this)

        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=adapter

        for ( i in 0..9 ){
            val modelClass=ModelClass
            modelClass.name="juhi"+i
            modelClass.name="seema"+i

            contactList!!.add((modelClass))
        }
        adapter!!.notifyDataSetChanged()




    }


}