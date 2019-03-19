package com.dxdevil.pd.prjp

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        fun onCreateOptionsMenu(menu: Menu): Boolean {
           val inflater = menuInflater
           inflater.inflate(R.menu.edit_icon, menu)
            return true
       }

        onOptionsItemSelected()


    }

}

