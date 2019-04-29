package com.dxdevil.pd.prjp

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.Model.AwOthers
import com.dxdevil.pd.prjp.Model.Completed
import com.dxdevil.pd.prjp.data.DocumentListAdapter
import com.dxdevil.pd.prjp.Model.Document
import com.dxdevil.pd.prjp.Model.DueSoon
import com.dxdevil.pd.prjp.Model.Request.Document.ListOfDocument
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.ListOfDocumentResponse
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_dashboarrd.*
import kotlinx.android.synthetic.main.activity_doc.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.content_docactivity.*
import kotlinx.android.synthetic.main.content_setting.*
import kotlinx.android.synthetic.main.item_notification.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocActivity : AppCompatActivity() {
    private var adapter: DocumentListAdapter? = null
    private var documentList: ArrayList<com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private  var size:Int=0
    private var docname:String?=null
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc)



        drawerLayout = findViewById(R.id.drawer_layout_document)
        ntoggle= ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var profilestring = getSharedPreferences("Token",0).getString("profileimage","")
        var bytearray = Base64.decode(profilestring, Base64.DEFAULT)
        var btmap = BitmapFactory.decodeByteArray(bytearray,0,bytearray.size)

        var navid = findViewById<NavigationView>(R.id.nav_view_document)
        var h = navid.getHeaderView(0)
        var inagev = h.findViewById<CircleImageView>(R.id.imageview_header)
        inagev!!.setImageBitmap(btmap)

        var htv = h.findViewById<TextView>(R.id.header_nametv)
        var htvem = h.findViewById<TextView>(R.id.header_emailtv)
        htv!!.text =
            getSharedPreferences("Token",0).getString("fname","").toString()+getSharedPreferences("Token",0).getString("lname","").toString()

        htvem!!.text =getSharedPreferences("Token",0).getString("email","")


        nav_view_document!!.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()


            when (menuItem.itemId) {
                R.id.dashboard -> {
                    startActivity(Intent(this@DocActivity,Dashboarrd::class.java))
                    drawer_layout_document.closeDrawer(GravityCompat.START)
                }
                R.id.documents -> {
                    drawer_layout_document.closeDrawer(GravityCompat.START)
                }
                R.id.contacts -> {
                    startActivity(Intent(this@DocActivity,Contacts::class.java))
                    drawer_layout_document.closeDrawer(GravityCompat.START)
                }
                R.id.settings -> {
                    startActivity(Intent(this@DocActivity,Contacts::class.java))
                    drawer_layout_document.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {
                    var sp = getSharedPreferences("Token", Context.MODE_PRIVATE)
                    sp.edit().remove("Token").apply()
                    sp.edit().remove("RefreshToken").apply()
                    startActivity(Intent(this@DocActivity,LoginActivity::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
            }


            true
        }



        mrecyclerView.layoutManager = layoutManager
        mrecyclerView.adapter = adapter



        var token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.N9w0jV/v+9PKjqTQVRprezt4+qqzzgZZjY68hHbb/VdLYi/P2x192in+N/puRDAp8zEKMMqdnOPVvgPE6C6bukPznNX+g+jkb5sv8FfeS7Q7UXY/QIHjeYLQJ4Tk0Aziq/VdzyrDQLOq20vUdEYJjwIBiq1pk+R/7xBYPpEEPI6lQtTxggOw72uJnYt/kwviC0i6ppoGmWZHU5sX4ZyNBnhSp5RPMYvQKENl5w3MdkGet6cb9MaMD55LM0Ytqy2DMHMqW4H1t4ql13hFw3c/1MhX/sNci8G6WNp1Bo6qnFwi+eu6ABRNaQ0C4lXKjy4K/9Mj9ZN6dqQLCWdpsbW6Bx1K46PitJsHNo6bqFVlUWyxRHNeIJKu655Ema646p9o62EeO4LDIrUhOJLPMpXXDwbdcgHAsLBpM/TMfOdQlaLsB2d6rOlkqlh0n8ForetQ+M19xiDH0C+YxrDeDlnY6v62VfXDwwayEVEgmjZv2SM/CKPoyQgHsYEh3tGXECqP9UVxmqL+jPbLhIrTlku4Z1LsZXmRVc/gEq1/NJPIU7DtF1PEkfIs4VUHuwGClKyyQ2GfWmecbn5RZ4WF4HZpOr8KUDo5TsLVee+4w0W75MRPSaQZVRBz/AaW5owhvQ1DsBzHmO01XfKxj5oXASQUaSOA6OoAyFgrA+JHMi3g10sjOVFPhiHSDyOPenpYcw7rrxB7pXpVEdnW1gen3oT5BSJ+PzMfFkt2sg0Mg4KRFMMG0/cG6wP5C1W2gKQT2hVENRxfTYeifEjLE0oTVot9g+xlczLrrvAjqJS9SuHk+nHNoS6E5sfspp65Z4MS78I7DzL6HmdWxpWyNuX0OHwUMTn4GcfSgO+7DNMU5JrHpyPNEElL6tJCPSTl+ERNA8WO.vpBM4TUrwCrUupzPXvKmD1TLxSPR93UFfgPROIJoN_s"
        var api = RetrofitClient.getInstance().api as Api
        var call = api.doclist(
            token, ListOfDocument(
                0,
                0,
                true,
                null,
                null,
                null,
                0,
                null,
                null,
                null
            )
        )as Call<ListOfDocumentResponse>


        try{
            call.enqueue(object : Callback<ListOfDocumentResponse> {
                override fun onFailure(call: Call<ListOfDocumentResponse>, t: Throwable) {
                    Toast.makeText(this@DocActivity, "Check your connection", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ListOfDocumentResponse>, response: Response<ListOfDocumentResponse>) {


                    if (response.isSuccessful) {
                        Toast.makeText(this@DocActivity,"Response is Successfull", Toast.LENGTH_SHORT).show()
                        try {
                            //  var documentList = ArrayList<ListOfDocumentResponse>(response.body()?.data)
                            layoutManager = LinearLayoutManager(this@DocActivity)
                            adapter = DocumentListAdapter(response.body()!!.data[0].documents, this@DocActivity)
                            documentList?.addAll(response.body()!!.data[0].documents)

                            for(i  in 0 .. 10)
                            {
                                val document =  com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document()

                                 documentList!!.add(document)
                            }
                            adapter!!.notifyDataSetChanged()

                        }catch (e:Exception)
                        {
                            Toast.makeText(this@DocActivity, e.message,Toast.LENGTH_LONG).show()
                        }

                    } else {
                        Toast.makeText(this@DocActivity, "Response is Failure", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }catch (e:Exception){
            Toast.makeText(this@DocActivity,"Excetion",Toast.LENGTH_SHORT).show()
        }
////////original code for recyclerview


//
//        for (i in 0..10) {
//            val document = Document()
//            document.docname = docname
//            documentList!!.add(document)
//        }
//        adapter!!.notifyDataSetChanged()
/////////////////////////////////////////////





    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(ntoggle.onOptionsItemSelected(item))
            return true

        when (item!!.itemId) {

            R.id.menu_awsign -> {
                Toast.makeText(applicationContext, "Awaiting my sign", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, DocActivity::class.java))

                return true
            }

            R.id.menu_awOthers -> {
                Toast.makeText(applicationContext, "Awaiting Others", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(applicationContext, AwOthers::class.java))
                startActivity(Intent(applicationContext,AwOthers::class.java))
                return true
            }

            R.id.menu_duesoon -> {
                Toast.makeText(applicationContext, "Due Soon", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, DueSoon::class.java))

                return true
            }

            R.id.menu_completed -> {
                Toast.makeText(applicationContext, "Completed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, Completed::class.java))

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
    override fun onKeyDown(keycode:Int, event: KeyEvent):Boolean {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
        }
        return super.onKeyDown(keycode, event)
    }

}
