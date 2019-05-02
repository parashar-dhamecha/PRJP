package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Response.UploadfileModel
import kotlinx.android.synthetic.main.activity_uploadfile.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import android.provider.MediaStore
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Environment
import android.webkit.MimeTypeMap
import android.net.Uri as Uri1
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Color.parseColor
import android.net.ConnectivityManager
import android.os.Build
import android.view.Menu
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.Response.ContactList
import com.dxdevil.pd.prjp.Model.Response.Data
import com.dxdevil.pd.prjp.com.dxdevil.pd.prjp.ObserverAdapter
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.signercv.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "DEPRECATION"
)
class uploadfile : AppCompatActivity(),View.OnClickListener {
    val READ_REQUEST_CODE =42
     var maxday:Int=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
     var maxmonth:Int=Calendar.getInstance().get(Calendar.MONTH)
    var maxyear:Int=Calendar.getInstance().get(Calendar.YEAR)
    var minday:Int=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    var minmonth:Int=Calendar.getInstance().get(Calendar.MONTH)
    var minyear:Int=Calendar.getInstance().get(Calendar.YEAR)


    lateinit var contactList :List<Data>
    lateinit var calander:Calendar
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploadfile)
//picking file
            val mimeTypes = arrayOf(
                "application/pdf",
                "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/vnd.ms-powerpoint",
                "application/x-excel"
                )

            val intent = Intent()
               .setAction(Intent.ACTION_GET_CONTENT).addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = if (mimeTypes.size === 1) mimeTypes[0] else "*/*"
            if (mimeTypes.size > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            }

            startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
        //selecting signers and observers
            getSignersData()
            eedatebutton.setOnClickListener(this)
        esdatebutton.setOnClickListener(this)
        signduedatebutton.setOnClickListener(this)
        timebutton.setOnClickListener(this)
        }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.eedatebutton->{
                var dpd :DatePickerDialog= DatePickerDialog(
                    this,
                    object : DatePickerDialog.OnDateSetListener{
                        @SuppressLint("SetTextI18n")
                        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                            if (minday <= dayOfMonth && minmonth <= month && minyear <= year) {
                                eedateet.setText("$year-$month-$dayOfMonth")
                                maxday = dayOfMonth
                                maxmonth = month
                                maxyear = year
                            }else{
                                eedateet.error = "endDate must be greater than start date"
                            }
                        }
                    },Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE))
                dpd.show()
            }
            R.id.esdatebutton->{
                var dpd :DatePickerDialog= DatePickerDialog(
                    this,
                    object : DatePickerDialog.OnDateSetListener{
                        @SuppressLint("SetTextI18n")
                        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                            if (maxday >= dayOfMonth && maxmonth >= month && maxyear >= year) {
                                esdateet.setText("$year-$month-$dayOfMonth")
                                minday = dayOfMonth
                                minmonth = month
                                minyear = year
                            }else{
                                esdateet.setError("endDate must be greater than start date")
                            }
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data
            if (selectedFile != null) {
                callapi(uri = selectedFile)
            }
        }
    }

    private fun callapi(uri : android.net.Uri) {

        val file1 = File(getRealPathFromURI(this,uri))

    Toast.makeText(this@uploadfile,file1.name,Toast.LENGTH_LONG).show()

        var rb =RequestBody.create(MediaType.parse(MimeTypeMap.getFileExtensionFromUrl(uri.toString())),file1)
        var rb2 =RequestBody.create(MediaType.parse("multipart/form-data"),file1.path)
        var mpb :MultipartBody.Part = MultipartBody.Part.createFormData("file",file1.name,rb)
        var token = getSharedPreferences("Token",0).getString("Token","").toString()


        var pd = ProgressDialog(this@uploadfile)
        pd.setCancelable(false)
        pd.setTitle("Uploading...")
        pd.setMessage(file1.name)
        pd.cardv1
        pd.isIndeterminate = true
        pd.show()
        var type=MimeTypeMap.getFileExtensionFromUrl(uri.toString())

        if(type=="pdf") {
            pd.setIcon(R.drawable.pdf3)
            filetypeiv!!.setImageResource(R.drawable.pdf3)
        }else if(type=="docx"|| type== "doc"){
            pd.setIcon(R.drawable.doc4)
            filetypeiv!!.setImageResource(R.drawable.doc4)
        }
        else if(type=="application/x-excel") {
            pd.setIcon(R.drawable.excel)
            filetypeiv!!.setImageResource(R.drawable.excel)
        }else if(type=="application/vnd.ms-powerpoint") {
            pd.setIcon(R.drawable.ppt2)
            filetypeiv!!.setImageResource(R.drawable.ppt2)
        }

        filenameet.setText(file1.name)


        var uapi = RetrofitClient.getInstance().api as Api
        var ucall = uapi.upload(token,mpb) as Call<UploadfileModel>
        ucall!!.enqueue(object : Callback<UploadfileModel>{

            override fun onFailure(call: Call<UploadfileModel>, t: Throwable) {
                startActivity(Intent(this@uploadfile,Dashboarrd::class.java))
                Toast.makeText(this@uploadfile,"Something went wrong please try again later" ,Toast.LENGTH_LONG).show()
                pd.dismiss()
            }

            override fun onResponse(call: Call<UploadfileModel>, response: Response<UploadfileModel>) {
            if(response.isSuccessful){
                var obj =response.body() as UploadfileModel

                Toast.makeText(this@uploadfile, response.body()!!.data[0].name.toString(),Toast.LENGTH_LONG).show()
                pd.dismiss()
            }
                else{
                Toast.makeText(this@uploadfile,response.message().toString(),Toast.LENGTH_LONG).show()
                startActivity(Intent(this@uploadfile,Dashboarrd::class.java))
                pd.dismiss()
            }
            }
        })

    }

    private fun getRealPathFromURI(context: Context, uri: android.net.Uri): String {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = this.getContentResolver().query(uri, filePathColumn, null, null, null)
        cursor.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val filePath = cursor.getString(columnIndex) as String
        cursor.close()
        return filePath
    }



    fun isNetworkAvailable(context: Context): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null
    }


    private fun setrv(contactList: List<Data>?) {

        val layoutManager = LinearLayoutManager(applicationContext)
        val layoutManager2 = LinearLayoutManager(applicationContext)
        signersrv.layoutManager = layoutManager
        observerrv.layoutManager= layoutManager2
        signersrv!!.adapter = SignerAdapter(this, contactList as ArrayList<Data>)
        observerrv!!.adapter = ObserverAdapter(this, contactList as ArrayList<Data>)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    fun getSignersData() {
        if (isNetworkAvailable(this@uploadfile)) {

            var token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")

            val api1 = RetrofitClient.getInstance()!!.api as Api
            var call1 = api1.getcontactresponse(token) as Call<ContactList>

            call1.enqueue(object : Callback<ContactList> {
                override fun onFailure(call: Call<ContactList>, t: Throwable) {
                    Toast.makeText(this@uploadfile, "Check your internet Connection", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<ContactList>, response: Response<ContactList>) = try {

                    if (response.isSuccessful) {
                        contactList = response.body()?.data as List<Data>
                        setrv(contactList)

                    } else {
                        startActivity(Intent(this@uploadfile,Dashboarrd::class.java))
                        Toast.makeText(this@uploadfile, "error:" + response.errorBody(), Toast.LENGTH_LONG).show()
                    }
                } catch (e: IOException) {
                    Toast.makeText(applicationContext, "Exception", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}


