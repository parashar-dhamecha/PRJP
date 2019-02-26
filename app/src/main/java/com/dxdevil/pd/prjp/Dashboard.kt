package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem


import android.view.View

import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.signpopup.*


class Dashboard : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle : ActionBarDrawerToggle

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val fab: View = findViewById(R.id.fab)

        drawerLayout = findViewById(R.id.drawerlayoutid)
        ntoggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
                    addsign.setOnClickListener {
                        var dialog :Dialog=Dialog(this)
                        dialog.setContentView(R.layout.signpopup)
                        dialog.setTitle("@string/addsign")
                        popuptv.setOnClickListener {
                            dialog.dismiss()
                        }
                        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        dialog.show()
                    }

            }
        }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(ntoggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }



    }


