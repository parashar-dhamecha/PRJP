package com.dxdevil.pd.prjp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_add_group.*
import kotlinx.android.synthetic.main.grp.*

class AddGroup : AppCompatActivity() {

    var contactList = ArrayList<ContactModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_group)




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
        recyclerView.layoutManager = layoutManager
        recyclerView!!.adapter = ContactsAdapter(this, contactList)

        for (i in 0..contactList.size) {

            contactList.add(
                ContactModel(

                    resources.getStringArray(R.array.names)[i], R.drawable.user

                )
            )
        }


    }
}