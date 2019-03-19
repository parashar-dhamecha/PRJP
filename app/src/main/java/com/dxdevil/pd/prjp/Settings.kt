package com.dxdevil.pd.prjp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        Change_Password.setOnClickListener{
            startActivity(Intent(applicationContext, ChangePassword::class.java))
        }
        My_Account.setOnClickListener{
            startActivity(Intent(applicationContext,ProfileActivity::class.java))
        }


    }

}