package com.dxdevil.pd.prjp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_add_group.*
import kotlinx.android.synthetic.main.activity_group.*

class GroupActivity : AppCompatActivity() {

    private var list = ArrayList<ContactModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        getIntentData()
    }

    private fun getIntentData() {
        if (intent != null && intent.getStringArrayListExtra("checkedContactList") != null) {

            list = intent.getSerializableExtra("checkedContactList") as ArrayList<ContactModel>

            //show group member name
            for (i in 0 until list.size) {
                println(list[i].name)
                Toast.makeText(this, list[i].name, Toast.LENGTH_SHORT)
            }


            val layoutManager = LinearLayoutManager(this)
            grp_recyclerView.layoutManager = layoutManager
            grp_recyclerView!!.adapter = GroupAdapter(this, list)

        }
    }
}
