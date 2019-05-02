package com.dxdevil.pd.prjp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dxdevil.pd.prjp.Model.Response.ContactList
import com.dxdevil.pd.prjp.Model.Response.Data
import kotlinx.android.synthetic.main.activity_contacts.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class Contacts : AppCompatActivity() {
    private var contactList = ArrayList<ContactModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        val actionbar = supportActionBar
        actionbar!!.title = "Contacts"
        // actionbar.setDisplayHomeAsUpEnabled(true)
        //actionbar.setDisplayHomeAsUpEnabled(true)


        contactList = ArrayList()

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
        r1!!.adapter = ContactsAdapter(this, contactList as ArrayList<Data>)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.addcontact, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.adduser -> {
                Toast.makeText(applicationContext, "Add Contact", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, AddContact::class.java))

                return true

            }
        }

        return super.onOptionsItemSelected(item)


    }

}






