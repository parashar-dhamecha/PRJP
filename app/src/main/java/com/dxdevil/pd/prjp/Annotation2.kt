package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.*
import android.widget.*
import com.dxdevil.pd.prjp.Model.Request.*
import com.dxdevil.pd.prjp.Model.Request.Document.NextPage
import com.dxdevil.pd.prjp.Model.Response.CreateResp
import com.dxdevil.pd.prjp.Model.Response.Document.NextPage.NextPageResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_annotation2.*
import kotlinx.android.synthetic.main.activity_dashboarrd.*
import kotlinx.android.synthetic.main.activity_preview.*
import kotlinx.android.synthetic.main.content_dashboarrd.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("ImplicitThis", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "UNCHECKED_CAST")
class Annotation2 : AppCompatActivity(), View.OnTouchListener {

    var viewarr: ArrayList<TextView> = arrayListOf()
    lateinit var viewpagearr: HashMap<Int, ArrayList<TextView>>
    lateinit var aasarr: ArrayList<String>
    lateinit var aslist: HashMap<Int, ArrayList<String>>
    lateinit var view: TextView
    lateinit var viewcount: MutableList<Int>
    lateinit var root: ViewGroup
    private var _xDelta: Int = 0
    private var _yDelta: Int = 0
    var selsigners: MutableList<String> = mutableListOf(String())
    var signersid: MutableList<String> = mutableListOf()
    var obid: MutableList<String> = mutableListOf()
    var dsm: MutableList<DocumentShapeModelXX> = mutableListOf()
    var title: String? = null
    var pageCount: Int = 0
    var token: String? = null
    var docId: String? = null
    var fromP: Int? = 1
    var toP: Int? = 6
    var cpage: Int = 1
    var currentpage: Int = 0
    var currentpage1: Int = 0
    var pageNo: Int = 0
    var length2: Int = 0
    var ispageannot: Array<Boolean> = arrayOf()
    var page: ArrayList<String> = ArrayList()
    lateinit var by: ByteArray
    lateinit var bitmap: Bitmap

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation2)
        root = findViewById(R.id.Relativelid)
        selsigners = this.intent.getStringArrayListExtra("ssname")
        signersid = this.intent.getStringArrayListExtra("ssid")
        obid = this.intent.getStringArrayListExtra("obid")
        aslist = HashMap()
        aasarr = arrayListOf()
        aslist[currentpage] = ArrayList()

        var sp = this.getSharedPreferences("CreateDocDetails", 0)
        docId = sp.getString("docid", "") as String
        token = this.getSharedPreferences("Token", 0).getString("Token", "").toString()

        prev_button.isEnabled = false


        apiNextPage(token, fromP.toString(), toP.toString())


        next_button!!.setOnClickListener {
            synchronized(this) {
                prev_button.isEnabled = true
                if (ispageannot[currentpage]) {
                    viewpagearr[currentpage] = arrayListOf()
                    aslist[currentpage] = arrayListOf()
                    aslist[currentpage] = aasarr
                    viewpagearr[currentpage] = viewarr
                    viewarr = arrayListOf()
                    aasarr = arrayListOf()
                    var fv = viewcount[currentpage]
                    while (fv > 0) {
                        root.removeView(findViewById((currentpage * 100) + fv - 1))
                        fv -= 1
                    }
                }

                currentpage += 1
                currentpage1 += 1

                if (ispageannot[currentpage]) {
                    var vc = 0

                    viewarr = ArrayList()
                    aasarr = arrayListOf()
                    aasarr = aslist[currentpage]!!
                    viewarr = viewpagearr[currentpage]!!
                    Toast.makeText(this@Annotation2, viewarr.size.toString(), Toast.LENGTH_LONG).show()
                    var sp = viewcount[currentpage]
                    while (sp > 0) {
                        root.addView(viewpagearr[currentpage]!!?.get(sp - 1))
                        sp -= 1
                    }
                }

                if (pageNo % 6 == 0 && pageNo
                    == page.size
                ) {
                    fromP = cpage + 1
                    toP = cpage + 6
                    apiNextPage(token, fromP.toString(), toP.toString())


                } else {
                    by = Base64.decode(page[currentpage], Base64.DEFAULT)
                    bitmap = BitmapFactory.decodeByteArray(by, 0, by.size)
                    previewdocid.setImageBitmap(bitmap)
                    pageNo = pageNo.inc()

                }
                cpage = cpage.inc()
                if (pageNo == pageCount)
                    next_button.isEnabled = false
            }
        }

        prev_button.setOnClickListener {
            synchronized(this) {
                next_button.isEnabled = true
                if (ispageannot[currentpage]) {
                    viewpagearr[currentpage] = arrayListOf()
                    viewpagearr[currentpage] = viewarr
                    aslist[currentpage] = arrayListOf()
                    aslist[currentpage] = aasarr
                    aasarr = arrayListOf()
                    viewarr = arrayListOf()

                    var fv = viewcount[currentpage]

                    while (fv > 0) {
                        root.removeView(findViewById((currentpage * 100) + fv - 1))
                        fv -= 1
                    }
                }

                currentpage -= 1
                currentpage1 -= 1

                if (ispageannot[currentpage]) {

                    viewarr = viewpagearr[currentpage]!!
                    aasarr = arrayListOf()
                    aasarr = aslist[currentpage]!!
                    Toast.makeText(this@Annotation2, viewarr.size.toString(), Toast.LENGTH_LONG).show()
                    var sp = viewcount[currentpage]
                    while (sp > 0) {
                        root.addView(viewarr[sp - 1])
                        sp -= 1
                    }
                }

                by = Base64.decode(page[currentpage], Base64.DEFAULT)
                bitmap = BitmapFactory.decodeByteArray(by, 0, by.size)
                previewdocid.setImageBitmap(bitmap)
                pageNo = pageNo.dec()
                cpage = cpage.dec()
                if (pageNo == 1)
                    prev_button.isEnabled = false


            }
        }


        if (selsigners != null) {
            var adapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, selsigners)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            signerspinner.adapter = adapter!!
        }

        addantbutton.setOnClickListener {
            viewarr.add(viewcount[currentpage], addannotatio())
            viewarr[viewcount[currentpage]]!!.id = (currentpage * 100) + viewcount[currentpage]
            root.addView(viewarr[viewcount[currentpage]])
            aasarr.add(
                viewcount[currentpage],
                signersid.get(selsigners.indexOf(signerspinner.selectedItem.toString()))
            )
            viewarr[viewcount[currentpage]]!!.setOnTouchListener(this)
            viewcount.set(currentpage, (viewcount.elementAt(currentpage) + 1))
            if (!ispageannot[currentpage]) {
                ispageannot[currentpage] = true
            }
        }
        clearantbutton.setOnClickListener {
            synchronized(this) {
                if (viewcount[currentpage] > 0) {
                    root.removeView(findViewById((currentpage * 100) + viewcount[currentpage] - 1))
                    viewcount[currentpage] -= 1
                }
            }
        }
        clearallantbutton.setOnClickListener {
            synchronized(this) {
                while (viewcount[currentpage] > 0) {
                    root.removeView(findViewById((currentpage * 100) + viewcount[currentpage] - 1))
                    viewcount[currentpage] -= 1
                }
            }
        }
    }


    fun apiNextPage(toke: String?, pageFrom: String, pageTo: String) {
        val builder = android.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.progress_message)
        message.text = getString(R.string.Getting)
        builder.setView(dialogView)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()

        val api = RetrofitClient.getInstance()!!.api as Api
        val call = api.nextPages(
            token,
            NextPage(
                docId,
                pageFrom,
                pageTo
            )
        ) as Call<NextPageResponse>
        try {
            call.enqueue(object : Callback<NextPageResponse> {
                override fun onFailure(call: Call<NextPageResponse>, t: Throwable) {
                    dialog.dismiss()
                    tvSomethingWrong?.visibility = View.VISIBLE
                    tvRefresh!!.visibility = View.VISIBLE
                }

                override fun onResponse(call: Call<NextPageResponse>, response: Response<NextPageResponse>) {

                    dialog.dismiss()
                    if (response.isSuccessful) {
                        pageCount = response.body()!!.data[0].pageCount

                        length2 = response.body()!!.data[0].pages.size
                        length2 = length2.dec()
                        if (length2 == 0) button_next.isEnabled = false

                        for (i in 0..length2) {
                            page.add(response.body()!!.data[0].pages[i])
                        }
                        by = Base64.decode(page[pageNo], Base64.DEFAULT)
                        bitmap = BitmapFactory.decodeByteArray(by, 0, by.size)
                        previewdocid.setImageBitmap(bitmap)
                        pageNo = pageNo.inc()

                        viewcount = MutableList(pageCount) { 0 }
                        ispageannot = Array(pageCount) { false }
                        viewpagearr = HashMap()
                        dialog.dismiss()
                    } else {
                        dialog.dismiss()
                        if (response.message().toString() == "Unauthorized") {
                            startActivity(Intent(this@Annotation2, LoginActivity::class.java))
                        } else {
                            Toast.makeText(this@Annotation2, response.message().toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        } catch (e: Exception) {
            dialog.dismiss()
            Toast.makeText(this@Annotation2, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    @SuppressLint("ResourceType")
    @Synchronized
    private fun addannotatio(): TextView {
        view = TextView(this)
        view.text = signerspinner.selectedItem.toString()
        view.setTextColor(Color.parseColor("#F5E1C1"))
        setLayoutsize(220, 100)
        return view
    }

    @SuppressLint("ResourceAsColor")
    private fun setLayoutsize(width: Int, height: Int) {
        val layoutParams = RelativeLayout.LayoutParams(width, height)
        layoutParams.leftMargin = 0
        layoutParams.topMargin = 0
        layoutParams.bottomMargin = -250
        layoutParams.rightMargin = -250
        view.setLayoutParams(layoutParams)
        view.setBackgroundColor(R.color.DueSoon)
        view.x = 100F
        view.y = 100F
    }


    override fun onTouch(view: View, event: MotionEvent): Boolean {
        val X = event.rawX.toInt()
        val Y = event.rawY.toInt()
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val lParams = view.getLayoutParams() as RelativeLayout.LayoutParams
                _xDelta = X - lParams.leftMargin
                _yDelta = Y - lParams.topMargin

            }


            MotionEvent.ACTION_UP -> {
                var vx: Double = view.x.toDouble()
                var vy: Double = view.y.toDouble()
                var xp: Double
                xp = ((vx / root.width) * 100).toDouble()
                var yp: Double
                yp = ((vy / root.height) * 100).toDouble()
                var wp: Int = view.width * 100 / root.width
                val hp: Int = view.height * 100 / root.height
                Toast.makeText(this@Annotation2, "$wp $hp", Toast.LENGTH_LONG).show()
                dsm.add(
                    DocumentShapeModelXX(
                        view.x.toInt(),
                        xp,
                        view.y.toInt(),
                        yp,
                        view.width,
                        wp.toDouble(),
                        view.height,
                        hp.toDouble(),
                        currentpage,
                        "0.612903225806452"
                        ,
                        aasarr.get(view.id - (currentpage * 100)),
                        true,
                        "ESignature"
                    )
                )

//            view.setOnTouchListener(null)
//                view.setOnClickListener {
//                }
//                view.setOnTouchListener(this)

                if (view.x < 0) {
                    view.x = 1f
                }
                if (view.y < 0) {
                    view.y = 1f
                }
                if (view.y > 970) {
                    view.y = 970f
                }
                if (view.x > 535) {
                    view.x = 535f
                }
            }
            MotionEvent.ACTION_POINTER_DOWN -> {

            }
            MotionEvent.ACTION_POINTER_UP -> {

            }
            MotionEvent.ACTION_MOVE -> {
                val layoutParams = view.getLayoutParams() as RelativeLayout.LayoutParams
                layoutParams.leftMargin = X - _xDelta
                layoutParams.topMargin = Y - _yDelta
                layoutParams.rightMargin = -250
                layoutParams.bottomMargin = -250
                xycoordinates.text = "X:${view.x},Y:${view.y}"
                view.setLayoutParams(layoutParams)


            }
        }
        root.invalidate()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        menuInflater.inflate(R.menu.create, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.create -> {
                var sp = this.getSharedPreferences("CreateDocDetails", 0)
                var ofname = sp.getString("originalfilename", "") as String
                var filename = sp.getString("filename", "") as String
                var des = sp.getString("description", "") as String
                var endstartdate = sp.getString("endstartdate", "") as String
                var endexpdate = sp.getString("endexpdate", "") as String
                var sidningduedate = sp.getString("signingduedate", "") as String
                var seqpar = sp.getInt("seqpara", 1)
//            var reminddays =   sp.getString("reminddays","")?.toInt()
                var docid = sp.getString("docid", "") as String
                var ext = sp.getString("extension", "") as String
                var selsig = mutableListOf<String>()
                selsig.add("86BA7357-DD2D-481F-8042-FCB314C427C0")
                var selobs = mutableListOf<String>()
                selobs.add("4777C124-BA42-4586-806E-AAABAAFF0492")
                var totsign = mutableListOf<Int>()
                totsign.add(1)

                var token = this.getSharedPreferences("Token", 0).getString("Token", "").toString()

                var api = RetrofitClient.getInstance().api as Api
                var createcall = api.create(
                    token,
                    CreateReq(
                        docid,
                        ofname,
                        filename,
                        "." + ext,
                        des,
                        endstartdate,
                        endexpdate,
                        sidningduedate,
                        3,
                        dsm,
                        seqpar,
                        signersid,
                        obid,
                        totsign,
                        4.092356
                        ,
                        -56.062161,
                        "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36",
                        "61.12.66.6",
                        totsign
                    )
                ) as Call<CreateResp>

                createcall.enqueue(object : Callback<CreateResp> {
                    override fun onFailure(call: Call<CreateResp>, t: Throwable) {
                        Toast.makeText(this@Annotation2, "Check your connection ", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<CreateResp>, response: Response<CreateResp>) {
                        if (response.isSuccessful) {
                            startActivity(Intent(this@Annotation2, Dashboarrd::class.java))
                            Toast.makeText(this@Annotation2, response.body()!!.message.toString(), Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this@Annotation2, response.message(), Toast.LENGTH_LONG).show()
                        }

                    }
                })


            }
        }
        return super.onOptionsItemSelected(item)
    }
}

