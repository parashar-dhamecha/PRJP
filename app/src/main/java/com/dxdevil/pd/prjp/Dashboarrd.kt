package com.dxdevil.pd.prjp


import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.KeyEvent
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.Request.Document.ListOfDocument
import com.dxdevil.pd.prjp.Model.Response.DashboardResponse
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.ListOfDocumentResponse
import com.dxdevil.pd.prjp.Model.Response.UploadfileModel
import com.dxdevil.pd.prjp.data.RecentDocumentAdapter
import com.google.android.material.internal.ContextUtils
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_dashboarrd.*
import kotlinx.android.synthetic.main.activity_uploadfile.*
import kotlinx.android.synthetic.main.content_dashboarrd.*
import kotlinx.android.synthetic.main.signercv.*
import kotlinx.android.synthetic.main.signpopup.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.lang.Exception

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Dashboarrd : AppCompatActivity() {
    private var adapter: RecentDocumentAdapter? = null
    private lateinit var documentList: ArrayList<Document>

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboarrd)


        RecentDocProgress.visibility=View.VISIBLE

        drawerLayout = findViewById(R.id.drawer_layout)
        ntoggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var  permissions= arrayListOf<String>(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
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



        val isprofileimage = getSharedPreferences("Token", 0).getBoolean("isprofile", false)
        val profilestring = getSharedPreferences("Token", 0).getString("profileimage", "")
        val navid = findViewById<NavigationView>(R.id.nav_view)
        val h = navid.getHeaderView(0)
        val imageev = h.findViewById<CircleImageView>(R.id.imageview_header)
        if(!isprofileimage)imageev.setImageResource(R.drawable.user)
        else{
            val bytearray = Base64.decode(profilestring, Base64.DEFAULT)
            val btmap = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.size)
            imageev!!.setImageBitmap(btmap)
        }
        val htv = h.findViewById<TextView>(R.id.header_nametv)
        val htvem = h.findViewById<TextView>(R.id.header_emailtv)
        htv!!.text =
            getSharedPreferences("Token", 0).getString("fname", "").toString() + " " + getSharedPreferences(
                "Token",
                0
            ).getString("lname", "").toString()
        htvem!!.text = getSharedPreferences("Token", 0).getString("email", "")




        val intent = Intent(this@Dashboarrd, DocActivity::class.java)
        intent.putExtra("Source","DocActivity")
        awatingsigntv.setOnClickListener{

            intent.putExtra("Doc_status",0)
            startActivity(intent)
        }

        AwatingSign21.setOnClickListener{


            intent.putExtra("Doc_status",0)
            startActivity(intent)
        }


        awatingotherstv.setOnClickListener{

            intent.putExtra("Doc_status",3)
            startActivity(intent)
        }

        AwatingSign222.setOnClickListener{

            intent.putExtra("Doc_status",3)
            startActivity(intent)
        }
        completedtv.setOnClickListener {


            intent.putExtra("Doc_status",2)
            startActivity(intent)
        }

        AwatingSign.setOnClickListener {


            intent.putExtra("Doc_status",2)
            startActivity(intent)
        }

        duesoontv.setOnClickListener {
            intent.putExtra("Doc_status",6)
            startActivity(intent)
        }

        AwatingSign2.setOnClickListener {
            intent.putExtra("Doc_status",6)
            startActivity(intent)
        }

// DRAWER ACTIONS
        nav_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()


            when (menuItem.itemId) {
                R.id.dashboard -> {
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                R.id.documents -> {
                    startActivity(Intent(this@Dashboarrd, DocActivity::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                R.id.contacts -> {
                    try{ startActivity(Intent(this@Dashboarrd, Contacts::class.java))
                        drawer_layout.closeDrawer(GravityCompat.START)}catch (e:Exception){
                        Toast.makeText(this@Dashboarrd,e.message,Toast.LENGTH_LONG).show()
                    }

                }
                R.id.verify->{
                    startActivity(Intent(this@Dashboarrd, VerifyActivity::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                R.id.settings -> {
                    startActivity(Intent(this@Dashboarrd, Settings::class.java))
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    val builder= AlertDialog.Builder(this@Dashboarrd)
                    builder.setTitle("Are you sure you want to Logout?")
                    builder.setPositiveButton("Yes") { dialogInterface: DialogInterface?, i: Int ->


                        val sp = getSharedPreferences("Token", Context.MODE_PRIVATE)
                        sp.edit().remove("Token").apply()
                        sp.edit().remove("RefreshToken").apply()

                        var sp2 = getSharedPreferences("Login Details", 0).edit()
                        sp2.putString("email", "")
                        sp2.putString("password", "")
                        sp2.putString("rememberflag", "0")
                        sp2.apply()
                        startActivity(Intent(this@Dashboarrd, LoginActivity::class.java))
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }

                    builder.setNegativeButton("No") { dialogInterface: DialogInterface?, i:Int->
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            }
            true
        }

        val preference = getSharedPreferences("Token", Context.MODE_PRIVATE) as SharedPreferences
        val tok = preference.getString("Token", "")!!.toString() as String?

        apiRecentDocs(null,0,tok)

        val dapi = RetrofitClient.getInstance().api as Api
        val call = dapi.getDashboardCouts(tok) as Call<DashboardResponse>
        call.enqueue(object : Callback<DashboardResponse> {


            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                call.cancel()
                Toast.makeText(this@Dashboarrd, "Check your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if (response.isSuccessful) {
                    val ob = response.body() as DashboardResponse
                    awatingsigntv?.text = ob.data[0]!!.awaitingMySign.toString()
                    awatingotherstv?.text = ob.data[0]!!.awaitingOthers.toString()
                    completedtv?.text = ob.data[0]!!.completed.toString()
                    duesoontv?.text = ob.data[0]!!.expireSoon.toString()
                } else {
                    if (response.message().toString() == "Unauthorized") {
                        startActivity(Intent(this@Dashboarrd, LoginActivity::class.java))
                    } else {
                        Toast.makeText(this@Dashboarrd, response.message().toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        })



        uploadcvFAB.setOnClickListener { view ->
            var u : android.net.Uri = android.net.Uri.parse(Environment.getExternalStorageDirectory().toString() + "/Download/")
//picking file
            floatingActionMenu.close(true)
            val mimeTypes = arrayOf(
                "application/pdf",
                "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/vnd.ms-powerpoint",
                "application/x-excel"
            )



            val intent = Intent()
                .setAction(Intent.ACTION_GET_CONTENT).addCategory(Intent.CATEGORY_OPENABLE).setType("file/*")
            intent.type = if (mimeTypes.size === 1) mimeTypes[0] else "*/*"
            if (mimeTypes.size > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            }

            startActivityForResult(Intent.createChooser(intent, "File"), 111)

        }

        add.setOnClickListener {
            floatingActionMenu.close(true)
            startActivity(Intent(applicationContext,AddContact::class.java))
        }


        draw_signature?.setOnClickListener {
            Toast.makeText(applicationContext, "hello", Toast.LENGTH_LONG).show()
            startActivity(Intent(applicationContext, DrawSignature::class.java))
        }
        photobutton?.setOnClickListener {
            startActivity(Intent(applicationContext, PhotoActivity::class.java))
        }
        typebutton?.setOnClickListener {
            startActivity(Intent(applicationContext, Type::class.java))
        }
// OPENS POPUP
        addsignature.setOnClickListener {
            try {
                floatingActionMenu.close(true)
                val ft = supportFragmentManager.beginTransaction()
                val cf: ChooseDF = ChooseDF()
                cf.show(ft, "dialog")

            } catch (e: Exception) {
                Log.d("1", "exception $e")
            }
        }


    }


// API TO GET RECENT DOCUMENS
    fun apiRecentDocs(status:Int?, currentpage:Int, token:String? ){

        val api = RetrofitClient.getInstance().api as Api


        val call = api.doclist(
            token, ListOfDocument(
               null,
                0,
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

                    RecentDocProgress.visibility=View.GONE


                    Toast.makeText(this@Dashboarrd, "Check your connection", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ListOfDocumentResponse>, response: Response<ListOfDocumentResponse>) {

                    if (response.isSuccessful) {
                        try {
                            adapter = RecentDocumentAdapter(response.body()!!.data[0].documents, this@Dashboarrd )
                            documentList = response.body()!!.data[0].documents as ArrayList<Document>

                            recentDoc_recyclerView.layoutManager = LinearLayoutManager(this@Dashboarrd)
                            recentDoc_recyclerView.adapter = RecentDocumentAdapter(response.body()!!.data[0].documents, this@Dashboarrd)

                            adapter!!.notifyDataSetChanged()

                            RecentDocProgress.visibility=View.GONE
                        } catch (e: Exception) {

                            RecentDocProgress.visibility=View.GONE
                            Toast.makeText(this@Dashboarrd, e.message, Toast.LENGTH_LONG).show()
                        }

                    } else {
                        if (response.message().toString() == "Unauthorized") {
                            startActivity(Intent(this@Dashboarrd, LoginActivity::class.java))
                        } else {
                            Toast.makeText(this@Dashboarrd, response.message().toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        } catch (e: Exception) {
            Toast.makeText(this@Dashboarrd, e.message, Toast.LENGTH_SHORT).show()
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


//API TO UPLOAD FILE
    private fun callapi(uri : android.net.Uri) {

        val file1 = File(getRealPathFromURI(this@Dashboarrd, uri))


        var rb = RequestBody.create(MediaType.parse(MimeTypeMap.getFileExtensionFromUrl(uri.toString())), file1)
        var mpb: MultipartBody.Part = MultipartBody.Part.createFormData("file", file1.name, rb)
        var token = getSharedPreferences("Token", 0).getString("Token", "").toString()


        var pd = ProgressDialog(this@Dashboarrd)
        pd.setCancelable(false)
        pd.setTitle("Uploading...")
        pd.setMessage(file1.name)
        pd.cardv1
        pd.isIndeterminate = true
        pd.show()
        var type = MimeTypeMap.getFileExtensionFromUrl(uri.toString())

        if (type == "pdf") {
            pd.setIcon(R.drawable.pdf3)
        } else if (type == "docx" || type == "doc") {
            pd.setIcon(R.drawable.doc4)
        } else if (type == "application/x-excel") {
            pd.setIcon(R.drawable.excel)
        } else if (type == "application/vnd.ms-powerpoint") {
            pd.setIcon(R.drawable.ppt2)
        }


        var uapi = RetrofitClient.getInstance().api as Api
        var ucall = uapi.upload(token, mpb) as Call<UploadfileModel>
        ucall!!.enqueue(object : Callback<UploadfileModel> {

            override fun onFailure(call: Call<UploadfileModel>, t: Throwable) {
                startActivity(Intent(this@Dashboarrd, Dashboarrd::class.java))
                Toast.makeText(this@Dashboarrd, "Something went wrong please try again later", Toast.LENGTH_LONG)
                    .show()
                pd.dismiss()
            }

            override fun onResponse(call: Call<UploadfileModel>, response: Response<UploadfileModel>) {
                if (response.isSuccessful) {
                    var obj = response.body() as UploadfileModel
                    var docid = obj.data[0].id.toString()

                  var  sp = getSharedPreferences("CreateDocDetails",0).edit()
                    sp.putString("originalfilename", file1.nameWithoutExtension)
                        sp.putString("docid",docid)
                    sp.putString("extension",file1.extension)
                    sp.putString("type",type)
                    sp.commit()
                    startActivity(Intent(this@Dashboarrd,Uploadfile::class.java))
                    pd.dismiss()
                } else {
                    if (response.message().toString() == "Unauthorized") {
                        startActivity(Intent(this@Dashboarrd, LoginActivity::class.java))
                    } else {
                        Toast.makeText(this@Dashboarrd, response.message().toString(), Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@Dashboarrd, Dashboarrd::class.java))
                    }
                    pd.dismiss()
                }
            }
        })
    }

//TO GET PATH OF FILE
    @SuppressLint("RestrictedApi")
    fun getpath(uri: android.net.Uri): String? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = ContextUtils.getActivity(this)!!.getContentResolver().query(uri, filePathColumn, null, null, null)
        cursor.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val filePath = cursor.getString(columnIndex)
        cursor.close()
        return filePath
    }
// ANOTHER METHOD TO GET PATH OF FILE
    private fun getRealPathFromURI(context: Context, uri: android.net.Uri): String {


        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = this.getContentResolver().query(uri, filePathColumn, null, null, null)
        cursor.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val filePath = cursor.getString(columnIndex) as String?
        cursor.close()
        return filePath.toString()
    }
// TO FINISH ACTIVTY
    override fun onBackPressed() {
        this.finish()
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (ntoggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }


    override fun onKeyDown(keycode: Int, event: KeyEvent): Boolean {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
            this@Dashboarrd.finish()

        }
        return super.onKeyDown(keycode, event)
    }

}
