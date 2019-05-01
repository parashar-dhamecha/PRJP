package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.KeyEvent
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


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Settings : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle

    @SuppressLint("SetTextI18n")

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
                    drawer_layout_setting.closeDrawer(GravityCompat.START)
                }
                R.id.documents -> {
                    startActivity(Intent(this@Settings,DocActivity::class.java))
                    drawer_layout_setting.closeDrawer(GravityCompat.START)
                }
                R.id.contacts -> {
                    startActivity(Intent(this@Settings,Contacts::class.java))
                    drawer_layout_setting.closeDrawer(GravityCompat.START)
                }
                R.id.settings -> {
                    drawer_layout_setting.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {
                    var sp = getSharedPreferences("Token", Context.MODE_PRIVATE)
                    sp.edit().remove("Token").apply()
                    sp.edit().remove("RefreshToken").apply()
                    startActivity(Intent(this@Settings,LoginActivity::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
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
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(ntoggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keycode:Int, event: KeyEvent):Boolean {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
        }
        return super.onKeyDown(keycode, event)
    }
}
