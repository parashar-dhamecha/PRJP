package com.dxdevil.pd.prjp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.dxdevil.pd.prjp.Model.Response.DashboardResponse
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.signpopup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class Dashboard : AppCompatActivity() {
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
        var dapi = RetrofitClient.getInstance().api as Api
        var call= dapi.getDashboard(preference.getString("Token","")!!.toString()) as Call<DashboardResponse>
        call?.enqueue(object : Callback<DashboardResponse>{

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                Toast.makeText(this@Dashboard,"Check your connection",Toast.LENGTH_LONG)
            }

            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if(response.isSuccessful){
                var ob = response.body()
                    awatingsigntv?.text =ob!!.data[0]!!.awaitingMySign.toString()
                    awatingotherstv?.text = ob!!.data[0]!!.awaitingOthers.toString()
                    completedtv?.text = ob!!.data[0]!!.completed.toString()
                    duesoontv?.text = ob!!.data[0]!!.expireSoon.toString()
                    Toast.makeText(this@Dashboard,"Success",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@Dashboard,"error",Toast.LENGTH_LONG).show()
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
}















