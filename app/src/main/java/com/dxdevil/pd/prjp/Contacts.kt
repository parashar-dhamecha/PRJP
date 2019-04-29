package com.dxdevil.pd.prjp

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_dashboarrd.*
import kotlinx.android.synthetic.main.content_contacts.*
class Contacts : AppCompatActivity() {


    private var contactList = ArrayList<ContactModel>()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

//  Drawer Code
       drawerLayout = findViewById(R.id.drawer_layout_contacts)
       ntoggle= ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
       drawerLayout.addDrawerListener(ntoggle)
       ntoggle.syncState()
       supportActionBar!!.setDisplayHomeAsUpEnabled(true)

       var profilestring = getSharedPreferences("Token",0).getString("profileimage","")
       var bytearray = Base64.decode(profilestring, Base64.DEFAULT)
       var btmap = BitmapFactory.decodeByteArray(bytearray,0,bytearray.size)

       var navid = findViewById<NavigationView>(R.id.nav_view_contacts)
       var h = navid.getHeaderView(0)
       var inagev = h.findViewById<CircleImageView>(R.id.imageview_header)
       inagev!!.setImageBitmap(btmap)

       var htv = h.findViewById<TextView>(R.id.header_nametv)
       var htvem = h.findViewById<TextView>(R.id.header_emailtv)
       htv!!.text =
           getSharedPreferences("Token",0).getString("fname","").toString()+getSharedPreferences("Token",0).getString("lname","").toString()

       htvem!!.text =getSharedPreferences("Token",0).getString("email","")


       nav_view_contacts.setNavigationItemSelectedListener { menuItem ->
           menuItem.isChecked = true
           drawerLayout.closeDrawers()


           when (menuItem.itemId) {
               R.id.dashboard -> {
                   startActivity(Intent(this@Contacts,Dashboarrd::class.java))
                   drawer_layout_contacts.closeDrawer(GravityCompat.START)
               }
               R.id.documents -> {
                   startActivity(Intent(this@Contacts,DocActivity::class.java))
                   drawer_layout_contacts.closeDrawer(GravityCompat.START)
               }
               R.id.contacts -> {
                   drawer_layout_contacts.closeDrawer(GravityCompat.START)
               }
               R.id.settings -> {
                   startActivity(Intent(this@Contacts,Settings::class.java))
                   drawer_layout_contacts.closeDrawer(GravityCompat.START)
               }
               R.id.logout -> {
                   var sp = getSharedPreferences("Token", Context.MODE_PRIVATE)
                   sp.edit().remove("Token").apply()
                   sp.edit().remove("RefreshToken").apply()
                   startActivity(Intent(this@Contacts,LoginActivity::class.java))
                   drawer_layout.closeDrawer(GravityCompat.START)
               }
           }


           true
       }






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

// contacts

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



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(ntoggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }
    override fun onKeyDown(keycode:Int, event: KeyEvent):Boolean {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
        }
        return super.onKeyDown(keycode, event)
    }
}



