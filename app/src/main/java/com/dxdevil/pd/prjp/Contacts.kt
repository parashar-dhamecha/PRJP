package com.dxdevil.pd.prjp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dxdevil.pd.prjp.Model.Response.ContactList
import com.dxdevil.pd.prjp.Model.Response.Data
import com.dxdevil.pd.prjp.Model.Response.DeleteIdResponse
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.contactsadapter.*
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


            // var token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.N9w0jV/v+9PKjqTQVRprezt4+qqzzgZZjY68hHbb/VdLYi/P2x192in+N/puRDAp8zEKMMqdnOPVvgPE6C6bukPznNX+g+jkb5sv8FfeS7Q7UXY/QIHjeYLQJ4Tk0Aziq/VdzyrDQLOq20vUdEYJjwIBiq1pk+R/7xBYPpEEPI6lQtTxggOw72uJnYt/kwviC0i6ppoGmWZHU5sX4ZyNBnhSp5RPMYvQKENl5w3MdkGet6cb9MaMD55LM0Ytqy2DMHMqW4H1t4ql13hFw3c/1MhX/sNci8G6WNp1Bo6qnFwi+eu6ABRNaQ0C4lXKjy4K/9Mj9ZN6dqQLCWdpsbW6Bx1K46PitJsHNo6bqFVlUWyxRHNeIJKu655Ema646p9o62EeO4LDIrUhOJLPMpXXDwbdcgHAsLBpM/TMfOdQlaLsB2d6rOlkqlh0n8ForetQ+M19xiDH0C+YxrDeDlnY6v62VfXDwwayEVEgmjZv2SM/CKPoyQgHsYEh3tGXECqP9UVxmqL+jPbLhIrTlku4Z1LsZXmRVc/gEq1/NJPIU7DtF1PEkfIs4VUHuwGClKyyQ2GfWmecbn5RZ4WF4HZpOr8KUDo5TsLVee+4w0W75MRPSaQZVRBz/AaW5owhvQ1DsBzHmO01XfKxj5oXASQUaSOA6OoAyFgrA+JHMi3g10sjOVFPhiHSDyOPenpYcw7rrxB7pXpVEdnW1gen3oT5BSJ+PzMfFkt2sg0Mg4KRFMMG0/cG6wP5C1W2gKQT2hVENRxfTYeifEjLE0oTVot9g+xlczLrrvAjqJS9SuHk+nG8OzaWNZLILgKdVw6Fgyw6agSt3uOL+AK/XuNfxZpY4yU0x7LvK/z8UM5WINg+iS5i6GTorG5fJzzQDbIHu/Us.K-g9z5jLmV2yxNQowHFsCkcgCtDVql4XZEqv4d56uw0"
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
//        this.contactList.add(
//            ContactModel(
//                resources.getStringArray(R.array.names)[0]
//                , R.drawable.user
//            )
//        )
//        this.contactList.add(
//            ContactModel(
//                resources.getStringArray(R.array.names)[1], R.drawable.user
//            )
//        )
//        this.contactList.add(
//            ContactModel(
//                resources.getStringArray(R.array.names)[2], R.drawable.user
//            )
//        )
//        this.contactList.add(
//            ContactModel(
//                resources.getStringArray(R.array.names)[3], R.drawable.user
//            )
//        )


        val layoutManager = LinearLayoutManager(applicationContext)
        r1.layoutManager = layoutManager
        r1!!.adapter = ContactsAdapter(this, contactList as ArrayList<Data>)
        // r1.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))


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






