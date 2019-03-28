package com.dxdevil.pd.prjp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_add_group.*

import kotlinx.android.synthetic.main.grp.*

class AddGroup : AppCompatActivity(), ItemClickListener {
    override fun onItemClick(position: Int, isChecked: Boolean) {
        if (contactList.size != 0) {
            if (isChecked) {
                checkedContacts.add(contactList[position])
            } else {
                checkedContacts.remove(contactList[position])

            }
        }

    }


    var contactList = ArrayList<ContactModel>()
    var checkedContacts = ArrayList<ContactModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_group)
        setRecyclerView()
        CreateGroup.setOnClickListener {
            //toast the member name of group
            for (i in 0 until checkedContacts.size) {
                println(checkedContacts[i].name)
                Toast.makeText(this, checkedContacts[i].name, Toast.LENGTH_SHORT)
            }

            // send group member name to next activity groupActivity



            val intent = Intent(this,GroupActivity::class.java)
            intent.putExtra("checkedContactList",checkedContacts)
            startActivity(intent)

        }

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
        recyclerView.layoutManager = layoutManager



        recyclerView!!.adapter = AddGroupAdapter(this,contactList,this)


    }

}


