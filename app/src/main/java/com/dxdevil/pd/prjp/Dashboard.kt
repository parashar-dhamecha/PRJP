package com.dxdevil.pd.prjp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide

import android.view.*


import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.drawerlayout.widget.DrawerLayout

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_dashboard.*



class Dashboard : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle : ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        drawerLayout = findViewById(R.id.drawerlayoutid)
        ntoggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        uploadcv.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
            var ft = supportFragmentManager
            val cf:ChooseDF = ChooseDF()
            addsign.setOnClickListener {
                cf.show(ft,"choose fragment tag")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(ntoggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }



      /*  val draw = view.findViewById<Button>(R.id.draw_signature)
        draw.setOnClickListener{
            startActivity(Intent(getApplicationContext(),DrawSignature::class.java))
        }
        val pic = view.findViewById<Button>(R.id.photobutton)
        pic.setOnClickListener {
            this.startActivity(Intent(applicationContext,PhotoActivity::class.java))
        }
        val type = view.findViewById<Button>(R.id.typebutton)
        type.setOnClickListener {
            this.startActivity(Intent(applicationContext, Type::class.java))
        }
        */


    }













