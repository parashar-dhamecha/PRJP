package com.dxdevil.pd.prjp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_dashboarrd.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.content_setting.*


class Settings : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        drawerLayout = findViewById(R.id.drawer_layout_setting)
        ntoggle= ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        var profilestring = getSharedPreferences("Token",0).getString("profileimage","")
        var bytearray = Base64.decode(profilestring, Base64.DEFAULT)
        Toast.makeText(this@Settings,profilestring,Toast.LENGTH_LONG).show()
        var btmap = BitmapFactory.decodeByteArray(bytearray,0,bytearray.size)

        var navid = findViewById<NavigationView>(R.id.nav_view_settings)
        var h = navid.getHeaderView(0)
        var inagev = h.findViewById<CircleImageView>(R.id.imageview_header)
        inagev!!.setImageBitmap(btmap)

        var htv = h.findViewById<TextView>(R.id.header_nametv)
        var htvem = h.findViewById<TextView>(R.id.header_emailtv)
        htv!!.text =
            getSharedPreferences("Token",0).getString("fname","").toString()+getSharedPreferences("Token",0).getString("lname","").toString()

        htvem!!.text =getSharedPreferences("Token",0).getString("email","")

        nav_view_settings.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            when (menuItem.itemId) {
                R.id.dashboard -> {
                    startActivity(Intent(this@Settings,Dashboarrd::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                R.id.documents -> {

                }
                R.id.contacts -> {

                }
                R.id.settings -> {
                    Toast.makeText(this@Settings,"settings",Toast.LENGTH_LONG)
                    startActivity(Intent(this@Settings,Settings::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {

                }
            }


            true
        }


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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboarrd, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(ntoggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.dashboard -> {
                // Handle the camera action
            }
            R.id.documents -> {

            }
            R.id.contacts -> {

            }
            R.id.settings -> {
                Toast.makeText(this@Settings,"settings",Toast.LENGTH_LONG)
                startActivity(Intent(this@Settings,Settings::class.java))
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            R.id.logout -> {
                var sp = getSharedPreferences("Token", Context.MODE_PRIVATE)
                sp.edit().remove("Token").apply()
                sp.edit().remove("RefreshToken").apply()
                startActivity(Intent(this@Settings,LoginActivity::class.java))
                drawer_layout.closeDrawer(GravityCompat.START)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onKeyDown(keycode:Int, event: KeyEvent):Boolean {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}
