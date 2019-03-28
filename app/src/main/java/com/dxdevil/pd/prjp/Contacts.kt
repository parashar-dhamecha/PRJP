package com.dxdevil.pd.prjp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_contacts.*

class Contacts : AppCompatActivity() {

    private var contactList = ArrayList<ContactModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        adduser.setOnClickListener{

            val intent =Intent(this,AddContact::class.java)
            startActivity(intent)
        }

        grp.setOnClickListener{

            val intent=Intent(this,AddGroup::class.java)
            startActivity(intent)
        }

        setRecyclerView()


    }


        private fun setRecyclerView() {
        contactList.add(
            ContactModel(
                resources.getStringArray(R.array.names)[0]
                , R.drawable.user
            )
        )
        contactList.add(
            ContactModel(
                resources.getStringArray(R.array.names)[1], R.drawable.user
            )
        )
        contactList.add(
            ContactModel(
                resources.getStringArray(R.array.names)[2], R.drawable.user
            )
        )
        contactList.add(
            ContactModel(
                resources.getStringArray(R.array.names)[3], R.drawable.user
            )
        )

        val layoutManager = LinearLayoutManager(this)
        r1.layoutManager = layoutManager
        r1!!.adapter = ContactsAdapter(this, contactList)






    }



}