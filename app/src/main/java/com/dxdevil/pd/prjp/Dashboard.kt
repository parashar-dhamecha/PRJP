package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.dxdevil.pd.prjp.Model.Response.DashboardResponse
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.signpopup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import com.google.android.material.navigation.NavigationView




class Dashboard : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.dashboard -> {
            }
            R.id.editsignature -> {
            }
            R.id.documents-> {
            }
            R.id.contacts -> {
            }
            R.id.settings -> {
                Toast.makeText(this@Dashboard,"settings",Toast.LENGTH_LONG)
                startActivity(Intent(applicationContext,Settings::class.java))
                return true
            }
            R.id.logout ->{

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        drawerLayout = findViewById(R.id.drawerlayoutid)
        ntoggle= ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Api calling
        var preference = getSharedPreferences("Token", Context.MODE_PRIVATE) as SharedPreferences
        var tok =preference.getString("Token","")!!.toString() as String?

        var dapi = RetrofitClient.getInstance().api as Api
        var call= dapi.getDashboardCouts(tok) as Call<DashboardResponse>
        call?.enqueue(object : Callback<DashboardResponse>{


            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                call.cancel()
                Toast.makeText(this@Dashboard,"Check your connection",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if(response.isSuccessful){
                var ob = response.body() as DashboardResponse
                    awatingsigntv?.text =ob!!.data[0]!!.awaitingMySign.toString()
                    awatingotherstv?.text = ob!!.data[0]!!.awaitingOthers.toString()
                    completedtv?.text = ob!!.data[0]!!.completed.toString()
                    duesoontv?.text = ob!!.data[0]!!.expireSoon.toString()
                    Toast.makeText(this@Dashboard,"Success",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@Dashboard,response!!.body()!!.message!!.toString(),Toast.LENGTH_LONG).show()
                }
            }
        })


        uploadcv.setOnClickListener { view ->
           startActivity(Intent(applicationContext,SetAnnotation::class.java))
        }

        draw_signature?.setOnClickListener {
            Toast.makeText(applicationContext, "hello", Toast.LENGTH_LONG)
            startActivity(Intent(applicationContext, DrawSignature::class.java))
        }
        photobutton?.setOnClickListener {
            startActivity(Intent(applicationContext, PhotoActivity::class.java))
        }
        typebutton?.setOnClickListener {
            startActivity(Intent(applicationContext, Type::class.java))
        }

        addsign.setOnClickListener {
            try {
                var ft = supportFragmentManager.beginTransaction()
                val cf: ChooseDF = ChooseDF()
                cf.show(ft, "dialog")

            } catch (e: Exception) {
                Log.d("1", "exception $e")
            }
        }


    }



        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            if(ntoggle.onOptionsItemSelected(item))
                return true

            return super.onOptionsItemSelected(item)
        }

    override fun onBackPressed() {
        this@Dashboard.finish()
    }
}

















