package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.MenuItem
import android.webkit.MimeTypeMap
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.dxdevil.pd.prjp.Model.Response.UploadfileModel
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_verify.*
import kotlinx.android.synthetic.main.content_verify.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class VerifyActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var ntoggle: ActionBarDrawerToggle
    lateinit var docid :String


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        card1.setOnClickListener {


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
        }


        drawerLayout = findViewById(R.id.drawer_layout_verify)
        ntoggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(ntoggle)
        ntoggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val profilestring = getSharedPreferences("Token", 0).getString("profileimage", "")
        val navid = findViewById<NavigationView>(R.id.nav_view_verify)
        val h = navid.getHeaderView(0)
        val inagev = h.findViewById<CircleImageView>(R.id.imageview_header)
        //if (profilestring == "")
            inagev.setImageResource(R.drawable.user)
//        else {
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

        nav_view_verify.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()


            when (menuItem.itemId) {
                R.id.dashboard -> {
                    startActivity(Intent(this@VerifyActivity, Dashboarrd::class.java))
                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                }
                R.id.documents -> {
                    startActivity(Intent(this@VerifyActivity, DocActivity::class.java))
                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                }
                R.id.contacts -> {
                    startActivity(Intent(this@VerifyActivity, Contacts::class.java))
                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                }
                R.id.settings -> {
                    startActivity(Intent(this@VerifyActivity, Settings::class.java))
                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                }
                R.id.verify -> {
                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {

                    drawer_layout_verify.closeDrawer(GravityCompat.START)
                    val builder = AlertDialog.Builder(this@VerifyActivity)
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

                        startActivity(Intent(this@VerifyActivity, LoginActivity::class.java))
                        drawer_layout_verify.closeDrawer(GravityCompat.START)
                    }

                    builder.setNegativeButton("No") { dialogInterface: DialogInterface?, i: Int ->
                        drawer_layout_verify.closeDrawer(GravityCompat.START)
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            }


            true
        }


        button.setOnClickListener {
            val intent = Intent(this@VerifyActivity, Verify_list::class.java)
            intent.putExtra("filehash", edFilehash.text.toString())

            startActivity(intent)


        }

        button2.setOnClickListener {

            val intent = Intent(this@VerifyActivity, Verify_list::class.java)
            intent.putExtra("Transhash", edTransHash.text.toString())

            startActivity(intent)

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

    private fun callapi(uri: android.net.Uri) {

        val file1 = File(getRealPathFromURI(this, uri))

        Toast.makeText(this@VerifyActivity, file1.name, Toast.LENGTH_LONG).show()

        var rb = RequestBody.create(MediaType.parse(MimeTypeMap.getFileExtensionFromUrl(uri.toString())), file1)
        var rb2 = RequestBody.create(MediaType.parse("multipart/form-data"), file1.path)
        var mpb: MultipartBody.Part = MultipartBody.Part.createFormData("file", file1.name, rb)
        var token = getSharedPreferences("Token", 0).getString("Token", "").toString()


        var pd = ProgressDialog(this@VerifyActivity)
        pd.setCancelable(false)
        pd.setTitle("Uploading...")
        pd.isIndeterminate = true
        pd.show()


        var uapi = RetrofitClient.getInstance().api as Api
        var ucall = uapi.upload(token, mpb) as Call<UploadfileModel>
        ucall!!.enqueue(object : Callback<UploadfileModel> {
            override fun onFailure(call: Call<UploadfileModel>, t: Throwable) {

                Toast.makeText(this@VerifyActivity, "Something went wrong please try again later", Toast.LENGTH_LONG)
                    .show()
                pd.dismiss()
            }

            override fun onResponse(call: Call<UploadfileModel>, response: Response<UploadfileModel>) {
                if (response.isSuccessful) {
                    var obj = response.body() as UploadfileModel
                    docid = obj.data[0].id.toString()

                    Toast.makeText(this@VerifyActivity, response.body()!!.data[0].name.toString(), Toast.LENGTH_LONG)
                        .show()
                    pd.dismiss()
                } else {
                    Toast.makeText(this@VerifyActivity, response.message().toString(), Toast.LENGTH_SHORT).show()

                }
                pd.dismiss()
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




    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(ntoggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }
}

