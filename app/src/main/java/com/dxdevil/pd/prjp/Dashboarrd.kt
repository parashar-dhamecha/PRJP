package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.KeyEvent
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.dxdevil.pd.prjp.Model.Response.DashboardResponse
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_dashboard.addsign
import kotlinx.android.synthetic.main.activity_dashboard.awatingotherstv
import kotlinx.android.synthetic.main.activity_dashboard.awatingsigntv
import kotlinx.android.synthetic.main.activity_dashboard.completedtv
import kotlinx.android.synthetic.main.activity_dashboard.duesoontv
import kotlinx.android.synthetic.main.activity_dashboarrd.*
import kotlinx.android.synthetic.main.content_dashboarrd.*
import kotlinx.android.synthetic.main.signpopup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Dashboarrd : AppCompatActivity(){
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle
    @SuppressLint("SetTextI18n", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboarrd)



        drawerLayout = findViewById(R.id.drawer_layout)
        ntoggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val profilestring = getSharedPreferences("Token", 0).getString("profileimage", "")
        val bytearray = Base64.decode(profilestring, Base64.DEFAULT)
        var btmap = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.size)

        val navid = findViewById<NavigationView>(R.id.nav_view)
        val h = navid.getHeaderView(0)
        val inagev = h.findViewById<CircleImageView>(R.id.imageview_header)
        inagev!!.setImageBitmap(btmap)

        val htv = h.findViewById<TextView>(R.id.header_nametv)
        val htvem = h.findViewById<TextView>(R.id.header_emailtv)
        htv!!.text =
            getSharedPreferences("Token", 0).getString("fname", "").toString() + " " + getSharedPreferences(
                "Token",
                0
            ).getString("lname", "").toString()

        htvem!!.text = getSharedPreferences("Token", 0).getString("email", "")

        var intent = Intent(this@Dashboarrd, DocActivity::class.java)
        intent.putExtra("Source","DocActivity")
        awatingsigntv.setOnClickListener{

            intent.putExtra("Doc_status",0)
            startActivity(intent)
        }

        AwatingSign21.setOnClickListener{


            intent.putExtra("Doc_status",0)
            startActivity(intent)
        }


        awatingotherstv.setOnClickListener{

            intent.putExtra("Doc_status",3)
            startActivity(intent)
        }

        AwatingSign222.setOnClickListener{

            intent.putExtra("Doc_status",3)
            startActivity(intent)
        }
        completedtv.setOnClickListener {


            intent.putExtra("Doc_status",2)
            startActivity(intent)
        }

        AwatingSign.setOnClickListener {


            intent.putExtra("Doc_status",2)
            startActivity(intent)
        }

        duesoontv.setOnClickListener {
            intent.putExtra("Doc_status",6)
            startActivity(intent)
        }

        AwatingSign2.setOnClickListener {
            intent.putExtra("Doc_status",6)
            startActivity(intent)
        }



        nav_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()


            when (menuItem.itemId) {
                R.id.dashboard -> {
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                R.id.documents -> {
                    startActivity(Intent(this@Dashboarrd, DocActivity::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                R.id.contacts -> {
                    startActivity(Intent(this@Dashboarrd, Contacts::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                R.id.settings -> {
                    startActivity(Intent(this@Dashboarrd, Settings::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {
                    var sp = getSharedPreferences("Token", Context.MODE_PRIVATE)
                    sp.edit().remove("Token").apply()
                    sp.edit().remove("RefreshToken").apply()
                    startActivity(Intent(this@Dashboarrd, LoginActivity::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }

        var preference = getSharedPreferences("Token", Context.MODE_PRIVATE) as SharedPreferences
        var tok = preference.getString("Token", "")!!.toString() as String?

        var dapi = RetrofitClient.getInstance().api as Api
        var call = dapi.getDashboardCouts(tok) as Call<DashboardResponse>
        call.enqueue(object : Callback<DashboardResponse> {


            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                call.cancel()
                Toast.makeText(this@Dashboarrd, "Check your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if (response.isSuccessful) {
                    var ob = response.body() as DashboardResponse
                    awatingsigntv?.text = ob!!.data[0]!!.awaitingMySign.toString()
                    awatingotherstv?.text = ob!!.data[0]!!.awaitingOthers.toString()
                    completedtv?.text = ob!!.data[0]!!.completed.toString()
                    duesoontv?.text = ob!!.data[0]!!.expireSoon.toString()
                } else {
                    Toast.makeText(this@Dashboarrd, response.message().toString(), Toast.LENGTH_LONG).show()

                }
            }
        })


        uploadcvFAB.setOnClickListener { view ->
            startActivity(Intent(applicationContext,Uploadfile::class.java))
        }

        add.setOnClickListener {
            startActivity(Intent(applicationContext,AddContact::class.java))
        }


        draw_signature?.setOnClickListener {
            Toast.makeText(applicationContext, "hello", Toast.LENGTH_LONG).show()
            startActivity(Intent(applicationContext, DrawSignature::class.java))
        }
        photobutton?.setOnClickListener {
            startActivity(Intent(applicationContext, PhotoActivity::class.java))
        }
        typebutton?.setOnClickListener {
            startActivity(Intent(applicationContext, Type::class.java))
        }

        addsignature.setOnClickListener {
            try {
                var ft = supportFragmentManager.beginTransaction()
                val cf: ChooseDF = ChooseDF()
                cf.show(ft, "dialog")

            } catch (e: Exception) {
                Log.d("1", "exception $e")
            }
        }


        }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//        override fun onCreateOptionsMenu(menu: Menu): Boolean {
//            // Inflate the menu; this adds items to the action bar if it is present.
//            menuInflater.inflate(R.menu.dashboarrd, menu)
//            return true
//        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (ntoggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }


        override fun onKeyDown(keycode: Int, event: KeyEvent): Boolean {
            if (keycode == KeyEvent.KEYCODE_BACK) {
                moveTaskToBack(true)
            }
            return super.onKeyDown(keycode, event)
        }
    }




