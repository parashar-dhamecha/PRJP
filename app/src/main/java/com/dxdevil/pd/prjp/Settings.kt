package com.dxdevil.pd.prjp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboarrd.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_settings.*


class Settings : AppCompatActivity() {


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)



        myaccountcv.setOnClickListener {
            startActivity(Intent(applicationContext,ProfileActivity::class.java))
        }

        cardView2.setOnClickListener {
            startActivity(Intent(applicationContext,ChangePassword::class.java))
        }

        card_logout.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }

    }
}
