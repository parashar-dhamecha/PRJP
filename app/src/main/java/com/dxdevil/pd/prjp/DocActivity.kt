package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document
import com.dxdevil.pd.prjp.Model.Request.Document.ListOfDocument
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.ListOfDocumentResponse
import com.dxdevil.pd.prjp.data.*
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_doc.*
import kotlinx.android.synthetic.main.content_docactivity.*
import kotlinx.android.synthetic.main.content_docactivity.button_next
import kotlinx.android.synthetic.main.content_docactivity.button_previous
import kotlinx.android.synthetic.main.content_docactivity.cpage_number
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DocActivity : AppCompatActivity() {



    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle

   private var currentPage=0
    private var pageNo=1
    var totalPages:Int=0
    private var adapter: AllDocumentsAdapter? = null
    private lateinit var documentList: ArrayList<Document>
    lateinit var alldocs : List<Document>
    var token :String?= null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc)
        setTitle(R.string.documents)


        drawerLayout = findViewById(R.id.drawer_layout_document)
        ntoggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        token=getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")

        img_No_doc.visibility=View.GONE
        tvNo_doc.visibility=View.GONE

        val profilestring = getSharedPreferences("Token", 0).getString("profileimage", "")
        val navid = findViewById<NavigationView>(R.id.nav_view_doc)
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

        val mIntent=intent
        val source=intent.getStringExtra("Source")
        if(source=="DocActivity"){
            val docStatus=mIntent.getIntExtra("Doc_status",0)
            if(docStatus==0)
                title= getString(R.string.awaiting)
            if(docStatus==3)
                title= getString(R.string.awatingothers)
            if(docStatus==2)
                title= getString(R.string.completed)
            if(docStatus==6)
                title= getString(R.string.signingdue)

            apiCalling(docStatus,0, token)
        }else
        {
            title = getString(R.string.alldocs)
            apiCalling(null,0, token)

        }



        button_next.setOnClickListener {
            title= getString(R.string.alldocs)
            button_previous.isEnabled=true
                currentPage +=1
                apiCalling(null,currentPage,token)
                pageNo=pageNo.inc()
        }

      button_previous.setOnClickListener {
          title= getString(R.string.alldocs)
            currentPage -= 1
            apiCalling(null,currentPage,token)
          pageNo=pageNo.dec()
        }
        nav_view_doc.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            when (menuItem.itemId) {
                R.id.dashboard -> {
                    startActivity(Intent(this@DocActivity, Dashboarrd::class.java))
                    drawer_layout_document.closeDrawer(GravityCompat.START)
                }
                R.id.documents -> {
                    startActivity(Intent(this@DocActivity, DocActivity::class.java))
                    drawer_layout_document.closeDrawer(GravityCompat.START)
                }
                R.id.contacts -> {
                    startActivity(Intent(this@DocActivity, Contacts::class.java))
                    drawer_layout_document.closeDrawer(GravityCompat.START)
                }
                R.id.settings -> {
                    startActivity(Intent(this@DocActivity, Settings::class.java))
                    drawer_layout_document.closeDrawer(GravityCompat.START)
                }
                R.id.verify->{
                    startActivity(Intent(this@DocActivity, VerifyActivity::class.java))
                    drawer_layout_contacts.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {
                    drawer_layout_document.closeDrawer(GravityCompat.START)
                    val builder= AlertDialog.Builder(this@DocActivity)
                    builder.setTitle("Are you sure you want to Logout?")
                    builder.setPositiveButton("Yes") { dialogInterface: DialogInterface?, i: Int ->


                        val sp = getSharedPreferences("Token", Context.MODE_PRIVATE)
                        sp.edit().remove("Token").apply()
                        sp.edit().remove("RefreshToken").apply()
                        startActivity(Intent(this@DocActivity, LoginActivity::class.java))
                        drawer_layout_document.closeDrawer(GravityCompat.START)
                    }

                    builder.setNegativeButton("No") { dialogInterface: DialogInterface?, i:Int->
                        drawer_layout_document.closeDrawer(GravityCompat.START)
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            }


            true
        }

        swipeRefreshDocuments!!.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refreshItem()
            }
            private fun refreshItem() {
                apiCalling(null,0, token)
                title = getString(R.string.alldocs)
                itemLoadComplete()
            }
            private fun itemLoadComplete() {
                swipeRefreshDocuments!!.isRefreshing = false
            }
        })
       
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (ntoggle.onOptionsItemSelected(item))
            return true

        when (item!!.itemId) {

            R.id.allDocuments -> {
                apiCalling(null,currentPage,token)
                title= getString(R.string.alldocs)
                return true
            }

            R.id.menu_awaitingMySign -> {

                apiCalling(0,currentPage,token)
                title= getString(R.string.awatingsign)
                return true
            }

            R.id.menu_awaitingOthers -> {

                apiCalling(3,currentPage,token)
                title= getString(R.string.awatingothers)
                return true
            }

            R.id.menu_completed -> {

                apiCalling(2,currentPage,token)
                title= getString(R.string.completed)
                return true
            }


            R.id.menu_duesoon -> {

                apiCalling(6,currentPage,token)
                title= getString(R.string.signingdue)
                return  true
            }

            R.id.menu_Declined -> {

                apiCalling(7,currentPage,token)
                title= getString(R.string.Declined)
                return  true
              }
            }
        return super.onOptionsItemSelected(item)
        }

    @SuppressLint("InflateParams")
     fun apiCalling(status:Int?, currentpage:Int, token:String? ){



        val builder = android.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.progress_message)

        message.text = getString(R.string.Loading)
        builder.setView(dialogView)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()

        mrecyclerView.layoutManager = LinearLayoutManager(this@DocActivity)
        mrecyclerView.adapter = this.adapter


        val api = RetrofitClient.getInstance().api as Api


        val call = api.doclist(
            token, ListOfDocument(
                status,
                currentpage,
                true,
                null,
                null,
                null,
                0,
                null,
                null,
                null
            )
        ) as Call<ListOfDocumentResponse>

        try {
            call.enqueue(object : Callback<ListOfDocumentResponse> {
                override fun onFailure(call: Call<ListOfDocumentResponse>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(this@DocActivity, "Check your connection", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ListOfDocumentResponse>, response: Response<ListOfDocumentResponse>) {

                    if (response.isSuccessful) {
                        try {
                            adapter = AllDocumentsAdapter(response.body()!!.data[0].documents, this@DocActivity )
                            documentList = response.body()!!.data[0].documents as ArrayList<Document>

                            mrecyclerView.layoutManager = LinearLayoutManager(this@DocActivity)
                            mrecyclerView.adapter = AllDocumentsAdapter(response.body()!!.data[0].documents, this@DocActivity)

                            alldocs=response.body()!!.data[0].documents
                            totalPages=response.body()!!.data[0].totalPages

                            adapter!!.notifyDataSetChanged()

                            cpage_number.text=response.body()!!.data[0].currentPage.toString()
                            total_pages.text=response.body()!!.data[0].totalPages.toString()

                           if(response.body()!!.data[0].currentPage==1)
                                button_previous.isEnabled=false
                            if(cpage_number.text==total_pages.text)
                                button_next.isEnabled=false
                            if(cpage_number.text!=total_pages.text) {
                                button_next.isEnabled = true
                            }
                            if(response.body()!!.data[0].totalRows==0)
                            {
                                img_No_doc.visibility=View.VISIBLE
                                tvNo_doc.visibility=View.VISIBLE
                                mrecyclerView.visibility=View.GONE
                            }

                            if(response.body()!!.data[0].totalRows!=0)
                            {
                                img_No_doc.visibility=View.GONE
                                tvNo_doc.visibility=View.GONE
                                mrecyclerView.visibility=View.VISIBLE
                            }

                            dialog.dismiss()

                        } catch (e: Exception) {
                            dialog.dismiss()
                            Toast.makeText(this@DocActivity, e.message, Toast.LENGTH_LONG).show()
                        }

                    } else {
                        dialog.dismiss()
                        if (response.message().toString() == "Unauthorized") {
                            startActivity(Intent(this@DocActivity, LoginActivity::class.java))
                        } else {
                            Toast.makeText(this@DocActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        } catch (e: Exception) {
            Toast.makeText(this@DocActivity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
