package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_verify.*
import kotlinx.android.synthetic.main.content_verify.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class VerifyActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        drawerLayout = findViewById(R.id.drawer_layout_verify)
        ntoggle= ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val profilestring = getSharedPreferences("Token", 0).getString("profileimage", "")
        val navid = findViewById<NavigationView>(R.id.nav_view_verify)
        val h = navid.getHeaderView(0)
        val inagev = h.findViewById<CircleImageView>(R.id.imageview_header)
        if(profilestring=="")
            inagev.setImageResource(R.drawable.user)
        else{
            val bytearray = Base64.decode(profilestring, Base64.DEFAULT)
            val btmap = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.size)
            inagev!!.setImageBitmap(btmap)
        }
        val htv = h.findViewById<TextView>(R.id.header_nametv)
        val htvem = h.findViewById<TextView>(R.id.header_emailtv)
        htv!!.text =
            getSharedPreferences("Token", 0).getString("fname", "").toString() + " " + getSharedPreferences(
                "Token",
                0
            ).getString("lname", "").toString()
        htvem!!.text = getSharedPreferences("Token", 0).getString("email", "")

        nav_view_verify.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()


            when (menuItem.itemId) {
                R.id.dashboard -> {
                    startActivity(Intent(this@VerifyActivity,Dashboarrd::class.java))
                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                }
                R.id.documents -> {
                    startActivity(Intent(this@VerifyActivity,DocActivity::class.java))
                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                }
                R.id.contacts -> {
                    startActivity(Intent(this@VerifyActivity,Contacts::class.java))
                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                }
                R.id.settings -> {
                    startActivity(Intent(this@VerifyActivity,Settings::class.java))
                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                }
                R.id.verify->{
                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {

                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                    val builder= AlertDialog.Builder(this@VerifyActivity)
                    builder.setTitle("Are you sure you want to Logout?")
                    builder.setPositiveButton("Yes") { dialogInterface: DialogInterface?, i: Int ->


                        val sp = getSharedPreferences("Token", Context.MODE_PRIVATE)
                        sp.edit().remove("Token").apply()
                        sp.edit().remove("RefreshToken").apply()
                        startActivity(Intent(this@VerifyActivity, LoginActivity::class.java))
                        drawer_layout_verify.closeDrawer(GravityCompat.START)
                    }

                    builder.setNegativeButton("No") { dialogInterface: DialogInterface?, i:Int->
                        drawer_layout_verify.closeDrawer(GravityCompat.START)
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            }


            true
        }


        button.setOnClickListener{
            val intent = Intent(this@VerifyActivity, Verify_list::class.java)
            intent.putExtra("filehash",edFilehash.text.toString())

            startActivity(intent)


        }

        button2.setOnClickListener{

            val intent = Intent(this@VerifyActivity, Verify_list::class.java)
            intent.putExtra("Transhash",edTransHash.text.toString())

            startActivity(intent)

        }


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(ntoggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }
}
