package com.dxdevil.pd.prjp

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_uploadfile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri as Uri1
import android.net.ConnectivityManager
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.Response.ContactList
import com.dxdevil.pd.prjp.Model.Response.Data
import com.dxdevil.pd.prjp.com.dxdevil.pd.prjp.ObserverAdapter
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "DEPRECATION", "UNCHECKED_CAST")
class Uploadfile : AppCompatActivity(), View.OnClickListener, CheckboxselectedListener, ObserverListener {

    val READ_REQUEST_CODE =42
     var maxday:Int=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
     var maxmonth:Int=Calendar.getInstance().get(Calendar.MONTH)
    var maxyear:Int=Calendar.getInstance().get(Calendar.YEAR)
    var minday:Int=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    var minmonth:Int=Calendar.getInstance().get(Calendar.MONTH)
    var minyear:Int=Calendar.getInstance().get(Calendar.YEAR)
    lateinit var sp :SharedPreferences.Editor
    var seqpara: Int = 1
    lateinit var docid :String

    var ssname :ArrayList<String> = ArrayList<String>()
    var ssid :ArrayList<String> = ArrayList<String>()
    var obname: ArrayList<String> = ArrayList<String>()
    var obid: ArrayList<String> = ArrayList()

    lateinit var contactList: MutableList<Data>

    @SuppressLint("NewApi", "CommitPrefEdits")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploadfile)


        var  permissions= arrayListOf<String>(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE)


        var listPermissionsNeeded:MutableList<String> = mutableListOf()
        for (p in permissions) {
            var result = ContextCompat.checkSelfPermission(this,p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(),100 )
        }


         sp = getSharedPreferences("CreateDocDetails",0).edit()

        filenameet.setText(getSharedPreferences("CreateDocDetails",0).getString("filename","").toString())
var type = getSharedPreferences("CreateDocDetails",0).getString("type","").toString()
        if (type == "pdf") {
            filetypeiv!!.setImageResource(R.drawable.pdf3)
        } else if (type == "docx" || type == "doc") {
            filetypeiv!!.setImageResource(R.drawable.doc4)
        } else if (type == "application/x-excel") {
            filetypeiv!!.setImageResource(R.drawable.excel)
        } else if (type == "application/vnd.ms-powerpoint") {
            filetypeiv!!.setImageResource(R.drawable.ppt2)
        }

        getSignersData()
        // setting date and time
            eedatebutton.setOnClickListener(this)
        esdatebutton.setOnClickListener(this)
        signduedatebutton.setOnClickListener(this)
        timebutton.setOnClickListener(this)

        // setting sharedpreference
        if(sequentialrb.isSelected){
            seqpara = 1
        } else seqpara = 2
    }




    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.eedatebutton->{
                var dpd :DatePickerDialog= DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        if (minday <= dayOfMonth && minmonth <= month && minyear <= year) {
                            eedateet.setText("$year-$month-$dayOfMonth")
                            maxday = dayOfMonth
                            maxmonth = month
                            maxyear = year
                            eedateet.error=null
                        }else{
                            eedateet.error = "endDate must be greater than start date"
                        }
                    },Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE))
                dpd.show()
            }
            R.id.esdatebutton->{
                var dpd :DatePickerDialog= DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        if (maxday >= dayOfMonth && maxmonth >= month && maxyear >= year) {
                            esdateet.setText("$year-$month-$dayOfMonth")
                            minday = dayOfMonth
                            minmonth = month
                            minyear = year
                            esdateet.error=null
                        }else{
                            esdateet.setError("endDate must be greater than start date")
                        }
                    },Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE))
                dpd.show()
            }
            R.id.signduedatebutton->{
                var dpd :DatePickerDialog= DatePickerDialog(
                    this,
                    object : DatePickerDialog.OnDateSetListener{
                        @SuppressLint("SetTextI18n")
                        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                            if(maxday>=dayOfMonth && maxmonth>=month && maxyear>=year) {
                                signduedateet.setText("$year-$month-$dayOfMonth")
                                signduedateet.error=null
                            }else if(minday <= dayOfMonth && minmonth <= month && minyear <= year){
                                signduedateet.setError("signing date must be greater than  expiry start date")
                            }

                            else{
                                signduedateet.setError("signing date must be less than expiry end date")
                            }
                                }
                    },Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE))
                dpd.show()
            }
            R.id.timebutton->{
                var tpd :TimePickerDialog= TimePickerDialog(
                    this,object: TimePickerDialog.OnTimeSetListener{
                        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                            timeet.setText("$hourOfDay:$minute")
                        }
                    },Calendar.getInstance().get(Calendar.HOUR_OF_DAY),Calendar.getInstance().get(Calendar.MINUTE),false)
                tpd.show()
            }
        }
    }


    fun isNetworkAvailable(context: Context): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null
    }


    fun getSignersData() {
        if (isNetworkAvailable(this@Uploadfile)) {

            var token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")

            val api1 = RetrofitClient.getInstance()!!.api as Api
            var call1 = api1.getcontactresponse(token) as Call<ContactList>

            call1.enqueue(object : Callback<ContactList> {
                override fun onFailure(call: Call<ContactList>, t: Throwable) {
                    Toast.makeText(this@Uploadfile, "Check your internet Connection", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<ContactList>, response: Response<ContactList>) = try {

                    if (response.isSuccessful) {
                        contactList = response.body()?.data as MutableList<Data>
                        setsig(contactList)

                    } else {
                        startActivity(Intent(this@Uploadfile,Dashboarrd::class.java))
                        Toast.makeText(this@Uploadfile, "error:" + response.errorBody(), Toast.LENGTH_LONG).show()
                    }
                } catch (e: IOException) {
                    Toast.makeText(applicationContext, "Exception", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        menuInflater.inflate(R.menu.next, menu)
        return true
    }

    private fun setsig(contactList: List<Data>?) {

        val layoutManager = LinearLayoutManager(applicationContext)
        val layoutManager2 = LinearLayoutManager(applicationContext)
        signersrv.layoutManager = layoutManager
        observerrv.layoutManager = layoutManager2
        var obj = SignerAdapter(this, contactList as ArrayList<Data>, this)
        signersrv!!.adapter = obj
        observerrv!!.adapter = ObserverAdapter(this, contactList, this)
    }

    override fun oncheckboxselected(data: Data?) {
        if (!obname.contains(data!!.name)) {
            ssname.add(data!!.name)
            ssid.add(data!!.userId)
        } else {
            Toast.makeText(this@Uploadfile, "user cant be both signer and observer", Toast.LENGTH_LONG).show()
        }
    }

    override fun onobserverselected(v: Data?) {
        if (!ssname.contains(v!!.name)) {
            obname.add(v!!.name)
            obid.add(v!!.userId)
        } else {
            Toast.makeText(this@Uploadfile, "user cant be both signer and observer", Toast.LENGTH_LONG).show()
        }
    }

    override fun oncheckboxunselected(data: Data?) {
        ssname.remove(data!!.name)
        ssid.remove(data!!.userId)
    }

    override fun onobserverunselected(v: Data?) {
        obname.remove(v!!.name)
        obid.remove(v!!.userId)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.next_icon ->{
                sp.putString("filename", filenameet.text.toString())
                sp.putString("description",filedescet!!.text.toString())
                sp.putString("endstartdate",esdateet.text!!.toString()+" "+timeet.text.toString())
                sp.putString("endexpdate",eedateet.text.toString()+" "+timeet.text.toString())
                sp.putString("signingduedate",signduedateet.text.toString()+" "+timeet.text.toString())
                sp.putInt("seqpara", seqpara)
                sp.putString("reminddays",reminddays.text.toString())
                sp.commit()
                var intent= Intent(this@Uploadfile,Annotation2::class.java)
                intent.putExtra("ssname",ssname)
                intent.putExtra("ssid",ssid)
                intent.putExtra("obname", obname)
                intent.putExtra("obid", obid)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            100 -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Snackbar.make(scrollView2,"Storage Permission Denied", Snackbar.LENGTH_LONG).show()
                } else {
                    Snackbar.make(scrollView2,"Storage Permission Granted", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }


}








