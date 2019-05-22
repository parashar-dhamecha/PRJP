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
import androidx.recyclerview.widget.RecyclerView
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
import kotlinx.android.synthetic.main.row_doclilst2.*
import kotlinx.android.synthetic.main.row_doclist.*
import kotlinx.android.synthetic.main.row_doclist.doc_status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DocActivity : AppCompatActivity() {



    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle

    var countsAwaitigMy=1
    var countsAwaitOthers=1
    var countsCompleted=1
    var countsDuesoon=1

    var totalPageAwatMY=0
    var totalPageAwatOthers=0
    var totalPageCompleted=0
    var totalPagesDuesoon=0

    private var currentPage=0
    private var pageNo=1
    var totalPages:Int=0
    private var adapter: AllDocumentsAdapter? = null
    private lateinit var documentList: ArrayList<Document>
    lateinit var alldocs : List<Document>
    var token :String?= null
    var docStatus:Int? = 0

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
        btnsConstraintLayout.visibility=View.GONE

        val profilestring = getSharedPreferences("Token", 0).getString("profileimage", "")
        val navid = findViewById<NavigationView>(R.id.nav_view_doc)
        val h = navid.getHeaderView(0)
        val inagev = h.findViewById<CircleImageView>(R.id.imageview_header)
      //  if(profilestring=="")
            inagev.setImageResource(R.drawable.user)
//        else{
//            val bytearray = Base64.decode(profilestring, Base64.DEFAULT)
//            val btmap = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.size)
//            inagev!!.setImageBitmap(btmap)
//        }
        val htv = h.findViewById<TextView>(R.id.header_nametv)
        val htvem = h.findViewById<TextView>(R.id.header_emailtv)
        htv!!.text =
            getSharedPreferences("Token", 0).getString("fname", "").toString() + " " + getSharedPreferences(
                "Token",
                0
            ).getString("lname", "").toString()
        htvem!!.text = getSharedPreferences("Token", 0).getString("email", "")

        val mIntent=intent


        countsAwaitigMy = getSharedPreferences("Counts",0).getInt("countsAwaitigMy",0)
        countsAwaitOthers = getSharedPreferences("Counts",0).getInt("countsAwaitOthers",0)
        countsCompleted=getSharedPreferences("Counts",0).getInt("countsCompleted",0)
        countsDuesoon =getSharedPreferences("Counts",0).getInt("countsDuesoon",0)


        val source=intent.getStringExtra("Source")
        if(source=="DocActivity"){
            docStatus=mIntent.getIntExtra("Doc_status",0)

            if(docStatus==0){
                title= getString(R.string.awaiting)
                totalPageAwatMY = if(countsAwaitigMy%10==0)
                    countsAwaitigMy/10
                else
                    (countsAwaitigMy/10)+1
                total_pages.text=totalPageAwatMY.toString()
            }

            if(docStatus==3){
                title= getString(R.string.awatingothers)
                totalPageAwatOthers=if(countsAwaitOthers%10==0)
                    countsAwaitOthers/10
                else
                    (countsAwaitOthers/10)+1
                total_pages.text=totalPageAwatOthers.toString()
            }

            if(docStatus==2){
                title= getString(R.string.completed)
                totalPageCompleted=if(countsCompleted%10==0)
                    countsCompleted/10
                else
                    (countsCompleted/10)+1
                total_pages.text=totalPageCompleted.toString()
            }

            if(docStatus==6){
                title= getString(R.string.signingdue)
                totalPagesDuesoon=if(countsDuesoon%10==0)
                    countsDuesoon/10
                else
                    (countsDuesoon/10)+1
                total_pages.text=totalPagesDuesoon.toString()
            }


            apiCalling(docStatus,0, token)
        }else
        {
            title = getString(R.string.alldocs)
            docStatus=null
            apiCalling(docStatus,0, token)
            total_pages.text=totalPages.toString()

        }


        button_next.setOnClickListener {

            button_previous.isEnabled=true
            currentPage +=1
            apiCalling(docStatus,currentPage,token)
            pageNo=pageNo.inc()
        }

        button_previous.setOnClickListener {

            currentPage -= 1
            apiCalling(docStatus,currentPage,token)
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
                    drawer_layout_document.closeDrawer(GravityCompat.START)
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
                docStatus=null
                currentPage=0
                apiCalling(docStatus,currentPage, token)
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
                currentPage=0
                apiCalling(null,currentPage,token)
                title= getString(R.string.alldocs)
                return true
            }

            R.id.menu_awaitingMySign -> {
                currentPage=0
                docStatus=0
                totalPageAwatMY = if(countsAwaitigMy%10==0)
                    countsAwaitigMy/10
                else
                    (countsAwaitigMy/10)+1
                total_pages.text=totalPageAwatMY.toString()

                apiCalling(docStatus,currentPage,token)
                title= getString(R.string.awatingsign)

                return true
            }

            R.id.menu_awaitingOthers -> {
                currentPage=0
                docStatus=3
                totalPageAwatOthers=if(countsAwaitOthers%10==0)
                    countsAwaitOthers/10
                else
                    (countsAwaitOthers/10)+1
                total_pages.text=totalPageAwatOthers.toString()
                apiCalling(docStatus,currentPage,token)
                title= getString(R.string.awatingothers)
                return true
            }

            R.id.menu_completed -> {
                currentPage=0
                docStatus=2
                totalPageCompleted=if(countsCompleted%10==0)
                    countsCompleted/10
                else
                    (countsCompleted/10)+1
                total_pages.text=totalPageCompleted.toString()
                apiCalling(docStatus,currentPage,token)
                title= getString(R.string.completed)
                return true
            }


            R.id.menu_duesoon -> {
                currentPage=0
                docStatus=6
                totalPagesDuesoon=if(countsDuesoon%10==0)
                    countsDuesoon/10
                else
                    (countsDuesoon/10)+1
                total_pages.text=totalPagesDuesoon.toString()
                apiCalling(docStatus,currentPage,token)
                title= getString(R.string.signingdue)
                return  true
            }

            R.id.menu_Declined -> {
                currentPage=0
                docStatus=7
                apiCalling(docStatus,currentPage,token)
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

        btnsConstraintLayout.visibility=View.GONE

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

                            btnsConstraintLayout.visibility=View.VISIBLE

                            totalPages=response.body()!!.data[0].totalPages

                            adapter!!.notifyDataSetChanged()

                            cpage_number.text=response.body()!!.data[0].currentPage.toString()

                            if(status==null)
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
                                btnsConstraintLayout.visibility=View.GONE
                            }else
                                btnsConstraintLayout.visibility=View.VISIBLE



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
