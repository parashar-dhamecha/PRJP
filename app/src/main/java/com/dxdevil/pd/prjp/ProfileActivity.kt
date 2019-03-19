package com.dxdevil.pd.prjp

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(profileToolbar as Toolbar)



        // Now get the support action bar
        val actionBar = supportActionBar


        actionBar!!.title = "Hello APP"


        actionBar.elevation = 4.0F


        actionBar.run {
            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
        }



    }

}

