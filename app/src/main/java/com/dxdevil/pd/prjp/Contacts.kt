package com.dxdevil.pd.prjp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.Response.AddContactResponse
import com.dxdevil.pd.prjp.Model.Response.ContactList
import com.dxdevil.pd.prjp.Model.Response.GetContactResponse
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.contactsadapter.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class Contacts : AppCompatActivity() {


    private var contactList = ArrayList<ContactModel>()
   // private var alertDialog: AlertDialog.Builder?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)


        contactList = ArrayList()







        adduser.setOnClickListener {

            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
        }

        grp.setOnClickListener {

            val intent = Intent(this, AddGroup::class.java)
            startActivity(intent)

        }

        setRecyclerView()

    }
     /*   var token1 = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")
       // var token2: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.N9w0jV/v+9PKjqTQVRprezt4+qqzzgZZjY68hHbb/VdLYi/P2x192in+N/puRDAp8zEKMMqdnOPVvgPE6C6bukPznNX+g+jkb5sv8FfeS7Q7UXY/QIHjeYLQJ4Tk0Aziq/VdzyrDQLOq20vUdEYJjwIBiq1pk+R/7xBYPpEEPI6lQtTxggOw72uJnYt/kwviC0i6ppoGmWZHU5sX4ZyNBnhSp5RPMYvQKENl5w3MdkGet6cb9MaMD55LM0Ytqy2DMHMqW4H1t4ql13hFw3c/1MhX/sNci8G6WNp1Bo6qnFwi+eu6ABRNaQ0C4lXKjy4K/9Mj9ZN6dqQLCWdpsbW6Bx1K46PitJsHNo6bqFVlUWyxRHNeIJKu655Ema646p9o62EeO4LDIrUhOJLPMpXXDwbdcgHAsLBpM/TMfOdQlaLsB2d6rOlkqlh0n8ForetQ+M19xiDH0C+YxrDeDlnY6v62VfXDwwayEVEgmjZv2SM/CKPoyQgHsYEh3tGXECqP9UVxmqL+jPbLhIrTlku4Z1LsZXmRVc/gEq1/NJPIU7DtF1PEkfIs4VUHuwGClKyyQ2GfWmecbn5RZ4WF4HZpOr8KUDo5TsLVee+4w0W75MRPSaQZVRBz/AaW5owhvQ1DsBzHmO01XfKxj5oXASQUaSOA6OoAyFgrA+JHMi3g10sjOVFPhiHSDyOPenpYcw7rrxB7pXpVEdnW1gen3oT5BSJ+PzMfFkt2sg0Mg4KRFMMG0/cG6wP5C1W2gKQT2hVENRxfTYeifEjLE0oTVot9g+xlczLrrvAjqJS9SuHk+nEQBBfgCUCsAsIIBHI0+eAKTP/lKOzoGBrUtxNNclN7pBfr8hKFI7WeeKlMcB/zeJyvdRBPfkm/VHIMD35jqsAj.QQUHMbNcAlFuEXGaqLGr1dVE2yulnZeYnvS71rsk1bY"
        val api1 = RetrofitClient.getInstance()!!.api as Api
        var call1 = api1.getcontactresponse(token1) as Call<GetContactResponse>
        call1?.enqueue(object : Callback<GetContactResponse> {
            override fun onFailure(call: Call<GetContactResponse>, t: Throwable) {

                Toast.makeText(this@Contacts, t.message, Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<GetContactResponse>, response: Response<GetContactResponse>) = try {

                if (response.isSuccessful) {

                    print("response"+response.body().toString())


                    var contactList = response.body()?.data as ContactList
                    r1!!.adapter = ContactsAdapter(contactList.contacts)
                    r1!!.layoutManager = LinearLayoutManager(this@Contacts)

                    println("contact list size" + contactList)

















                    // contactList= ContactModel(response.body.data[0].name.toString())
                    //  r1.adapter=ContactsAdapter(this@Contacts,response?.body().)

                    //ContactsAdapter.setContactsListItems(response.body()!!)

                    //var cont= response.body()?.data as AddContactDatum


                } else {
                    Toast.makeText(this@Contacts, "error", Toast.LENGTH_LONG).show()
                }
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "Exception", Toast.LENGTH_SHORT).show()
            }
        })
    }*/




        private fun setRecyclerView() {
     contactList.add(
         ContactModel(
             resources.getStringArray(R.array.names)[0]
             , R.drawable.user
         )
     )
     contactList.add(
         ContactModel(
             resources.getStringArray(R.array.names)[1], R.drawable.user
         )
     )
     contactList.add(
         ContactModel(
             resources.getStringArray(R.array.names)[2], R.drawable.user
         )
     )
     contactList.add(
         ContactModel(
             resources.getStringArray(R.array.names)[3], R.drawable.user
         )
     )


            val layoutManager = LinearLayoutManager(applicationContext)
            r1.layoutManager = layoutManager
            r1!!.adapter = ContactsAdapter(this, contactList)
            r1.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))



        }






}



