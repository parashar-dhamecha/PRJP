package com.dxdevil.pd.prjp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dxdevil.pd.prjp.Model.Response.ContactList
import com.dxdevil.pd.prjp.Model.Response.Data
import com.dxdevil.pd.prjp.Model.Response.DeleteIdResponse
import com.dxdevil.pd.prjp.Model.Response.GetContactIdResponse
import com.github.clans.fab.FloatingActionMenu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_dashboarrd.*
import kotlinx.android.synthetic.main.contactsadapter.*
import org.w3c.dom.Text
import kotlinx.android.synthetic.main.content_contacts.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Locale.filter

class Contacts : AppCompatActivity() {

    private var contactList = ArrayList<Data>()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle
    lateinit var onbj:ContactsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
         drawerLayout = findViewById(R.id.drawer_layout_contacts)
        ntoggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


         onbj = ContactsAdapter(this@Contacts,contactList)


        var fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)




        fab!!.setOnClickListener{
            val intent = Intent(this@Contacts, AddContact::class.java)
            startActivity(intent)
        }


        val actionbar = supportActionBar
        actionbar!!.title = "Contacts"


        contactList = ArrayList()


        search1.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(p0: String): Boolean {


                Toast.makeText(this@Contacts,"wrong ",Toast.LENGTH_LONG).show()



                onbj.filtereList1(p0)
               // onbj.notifyDataSetChanged()


                return false

            }


        })


        nav_view_contacts.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()


            when (menuItem.itemId) {
                R.id.dashboard -> {
                    startActivity(Intent(this@Contacts, Dashboarrd::class.java))
                    drawer_layout_contacts.closeDrawer(GravityCompat.START)
                }
                R.id.documents -> {
                    startActivity(Intent(this@Contacts, DocActivity::class.java))
                    drawer_layout_contacts.closeDrawer(GravityCompat.START)
                }
                R.id.contacts -> {
                    startActivity(Intent(this@Contacts, Contacts::class.java))
                    drawer_layout_contacts.closeDrawer(GravityCompat.START)
                }
                R.id.settings -> {
                    startActivity(Intent(this@Contacts, Settings::class.java))
                    drawer_layout_contacts.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {
                    var sp = getSharedPreferences("Token", Context.MODE_PRIVATE)
                    sp.edit().remove("Token").apply()
                    sp.edit().remove("RefreshToken").apply()
                    startActivity(Intent(this@Contacts, LoginActivity::class.java))
                    drawer_layout_contacts.closeDrawer(GravityCompat.START)
                }


            }


            true
        }

        //progressbar.visibility = View.VISIBLE
        // getContactApi()

        loadData()
        swipeRefresh!!.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refreshItem()
            }

            private fun refreshItem() {
                loadData()
                itemLoadComplete()
            }


            private fun itemLoadComplete() {
                swipeRefresh!!.isRefreshing = false
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (ntoggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        val myIntent = Intent(this@Contacts, Dashboarrd::class.java)

        myIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(myIntent)
        finish()
        return
    }


        fun isNetworkAvailable(context: Context): Boolean {
            return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null
        }

        fun loadData() {
            if (isNetworkAvailable(this@Contacts)) {

                var token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")
                swipeRefresh!!.isRefreshing = true
                swipeRefresh.visibility = View.VISIBLE


                val api1 = RetrofitClient.getInstance()!!.api as Api
                var call1 = api1.getcontactresponse(token) as Call<ContactList>
                call1.enqueue(object : Callback<ContactList> {

                    override fun onFailure(call: Call<ContactList>, t: Throwable) {
                        //      progressbar.visibility = View.GONE
                        Toast.makeText(this@Contacts, "Check your internet Connection", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<ContactList>, response: Response<ContactList>) = try {

                        swipeRefresh!!.isRefreshing = false

                        //progressbar.visibility = View.GONE
                        if (response.isSuccessful) {
                            var contactList = response.body()?.data as List<Data>
                            println("contact list size$contactList")

                            setRecyclerView(contactList)

                        } else {
                            Toast.makeText(this@Contacts, "error" + response.errorBody(), Toast.LENGTH_LONG).show()
                        }
                    } catch (e: IOException) {
                        Toast.makeText(applicationContext, "Exception", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }


        private fun setRecyclerView(contactList: List<Data>?) {


            val layoutManager = LinearLayoutManager(applicationContext)
            r1.layoutManager = layoutManager
            onbj = ContactsAdapter(this, contactList as ArrayList<Data>)
            r1!!.adapter = onbj
            // r1.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))


        }


    }






