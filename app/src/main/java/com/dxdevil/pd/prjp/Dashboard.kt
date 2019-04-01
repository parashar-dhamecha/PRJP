package com.dxdevil.pd.prjp


import android.content.Context
import android.content.Intent

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide

import android.view.*
import android.widget.*


import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.marginLeft

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.signpopup.*
import kotlinx.android.synthetic.main.signpopup.view.*


class Dashboard : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle : ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        drawerLayout = findViewById(R.id.drawerlayoutid)
        ntoggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//         uploadcv.setOnClickListener { view ->
//            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .show()
//            }
        }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(ntoggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
    fun showdiag(view:View) {
        val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflate a custom view using layout inflater
        var view = inflater.inflate(R.layout.signpopup,null)
        // Initialize a new instance of popup window
        val popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.MATCH_PARENT, // Width of popup window
            LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 10.0F
        }


        // If API level 23 or higher then execute the code
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            // Create a new slide animation for popup window enter transition
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn

            // Slide animation for popup window exit transition
            val slideOut = Slide()
            slideOut.slideEdge = Gravity.RIGHT
            popupWindow.exitTransition = slideOut

        }
        val tv = view.findViewById<TextView>(R.id.close_tvid)
        tv.setOnClickListener {
            popupWindow.dismiss()

        }
        val draw = view.findViewById<Button>(R.id.draw_signature)
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
        var vg:ViewGroup =findViewById(R.id.linearLayout)

        popupWindow.showAtLocation(vg,Gravity.CENTER,0,0)
        popupWindow.isFocusable

    }
}










