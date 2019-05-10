package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document
import com.dxdevil.pd.prjp.Model.Request.Document.ListOfDocument
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.ListOfDocumentResponse
import com.dxdevil.pd.prjp.data.*
import kotlinx.android.synthetic.main.activity_dashboarrd.*
import kotlinx.android.synthetic.main.activity_doc.*
import kotlinx.android.synthetic.main.content_docactivity.*
import kotlinx.android.synthetic.main.row_doclist.*


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DocActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle

   private var currentPage=0
    var totalPages:Int=0

    private var adapter: AllDocumentsAdapter? = null
    private lateinit var documentList: ArrayList<Document>

    lateinit var alldocs : List<Document>
    var token :String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc)
        setTitle(R.string.documents)

        val st = getSharedPreferences("Doc_status", 0) as SharedPreferences

        drawerLayout = findViewById(R.id.drawer_layout_document)
        ntoggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        token=getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")

        img_No_doc.visibility=View.GONE
        tvNo_doc.visibility=View.GONE


        val mIntent=intent
        val source=intent.getStringExtra("Source")
        if(source=="DocActivity"){
            val docStatus=mIntent.getIntExtra("Doc_status",0)
            apiCalling(docStatus,0, token)
        }else
        {
            apiCalling(null,0, token)
        }




        button_next.setOnClickListener {
            button_previous.isEnabled=true
                currentPage +=1
                apiCalling(null,currentPage,token)}

      button_previous.setOnClickListener {
            currentPage -= 1
            apiCalling(null,currentPage,token)
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
                R.id.logout -> {
                    val sp = getSharedPreferences("Token", Context.MODE_PRIVATE)
                    sp.edit().remove("Token").apply()
                    sp.edit().remove("RefreshToken").apply()
                    startActivity(Intent(this@DocActivity, LoginActivity::class.java))
                    drawer_layout_document.closeDrawer(GravityCompat.START)

                }
            }


            true
        }
       
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
                return true
            }

            R.id.menu_awaitingMySign -> {
                apiCalling(0,currentPage,token)
                return true
            }

            R.id.menu_awaitingOthers -> {
                apiCalling(3,currentPage,token)
                return true
            }

            R.id.menu_completed -> {

                apiCalling(2,currentPage,token)
                return true
            }


            R.id.menu_duesoon -> {
                apiCalling(6,currentPage,token)
                return  true
            }

            R.id.menu_Declined -> {
                apiCalling(7,currentPage,token)
                return  true
              }
            }
        return super.onOptionsItemSelected(item)
        }

    @SuppressLint("InflateParams")
    private fun apiCalling(status:Int?, currentpage:Int, token:String? ){



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
                        Toast.makeText(this@DocActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            Toast.makeText(this@DocActivity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
