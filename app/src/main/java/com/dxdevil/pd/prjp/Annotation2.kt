package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.ArrayAdapter
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.CreateDocRequest
import com.dxdevil.pd.prjp.Model.Request.DocumentShapeModel
import com.dxdevil.pd.prjp.Model.Response.CreateDocResponse
import kotlinx.android.synthetic.main.activity_annotation2.*
import kotlinx.android.synthetic.main.activity_uploadfile.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


@Suppress("ImplicitThis", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "UNCHECKED_CAST"
)
class Annotation2 : AppCompatActivity(),View.OnTouchListener{

    lateinit var viewarr: ArrayList<ImageView>
    lateinit var view :ImageView
     var viewcount: Int = 0
    lateinit var root: ViewGroup
    private var _xDelta: Int = 0
    private var _yDelta: Int = 0
    var currentpage = 1
     var selsigners : ArrayList<String>? = ArrayList()
    var signersid : ArrayList<String>? = ArrayList()
    var documentshapemodel : ArrayList<DocumentShapeModel> = ArrayList()
    var xa :ArrayList<Int> = ArrayList()
    var ya :ArrayList<Int> = ArrayList()
    var wa :ArrayList<Int> = ArrayList()
    var ha :ArrayList<Int> = ArrayList()
    var xp :ArrayList<Double> = ArrayList()
    var yp :ArrayList<Double> = ArrayList()
    var wp :ArrayList<Double> = ArrayList()
    var hp :ArrayList<Double> = ArrayList()



    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation2)
        root = findViewById(R.id.Relativelid)
       selsigners=this.intent.getStringArrayListExtra("ssname")
        signersid=this.intent.getStringArrayListExtra("ssid")
        viewarr=ArrayList<ImageView>()

        if(selsigners!=null) {
            var adapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, selsigners)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            signerspinner.adapter = adapter!!
        }

        previewdocid.setImageDrawable(getDrawable(R.drawable.sampledoc))

         addantbutton.setOnClickListener {
             synchronized(this) {
                 viewarr.add( addannotatio() as ImageView)
                 viewarr[viewcount].id= viewcount
                 root.addView(viewarr[viewcount])
                 viewarr[viewcount].setOnTouchListener(this)
                 viewcount+=1
             }
         }
        clearantbutton.setOnClickListener {
            synchronized(this) {
                if (viewcount > 0) {
                    root.removeView(findViewById(viewcount - 1))
                    viewcount -= 1
                }
            }
        }
      clearallantbutton.setOnClickListener {
         synchronized(this) {
             while (viewcount > 0) {
                 root.removeView(findViewById(viewcount-1))
                 viewarr.removeAt(viewcount-1)
                 viewcount -= 1
             }
         }
      }



    }

    @SuppressLint("ResourceType")
   @Synchronized private fun addannotatio():View {
        view = ImageView(this)
        view.setImageDrawable(getDrawable(R.drawable.logo))
        setLayoutsize(250,110)
        return view
    }

    @SuppressLint("ResourceAsColor")
    private fun setLayoutsize(width:Int,height:Int){
        val layoutParams = RelativeLayout.LayoutParams(width, height)
        layoutParams.leftMargin = 0
        layoutParams.topMargin = 0
        layoutParams.bottomMargin = -250
        layoutParams.rightMargin = -250
        view.setLayoutParams(layoutParams)
        view.setBackgroundColor(R.color.DueSoon)
        view.x= 100F
        view.y=100F
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
                xa.add(view.id, view.x.toInt())
                ya.add(view.id, view.y.toInt())
                var rw= view.width/1.4
                var rh:Double = (view.height/2).toDouble()
                xp.add(view.id,(((view.x*100)/rw)*root.width)/100)
                yp.add(view.id,(((view.y*100)/rh)*root.width)/100)
                wa.add(view.id,view.width)
               ha.add(view.id,view.height)
                wp.add(view.id,(100/rw)*100)
                hp.add(view.id,(100/rh)*100)
                documentshapemodel.add(view.id, DocumentShapeModel(242,31.002,663,38.33,369,65.21,104
                    ,15.22,1,"0.612",
                    signersid?.get(selsigners!!.indexOf(signerspinner!!.selectedItem)),true,"ESignature")
                )
                 Toast.makeText(this@Annotation2,documentshapemodel[view.id].x.toString(),Toast.LENGTH_LONG).show()


//            view.setOnTouchListener(null)
//                view.setOnClickListener {
//                }
//                view.setOnTouchListener(this)
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
                xycoordinates.text= "X:${view.x},Y:${view.y}"
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
            R.id.create ->{
                var sp = this.getSharedPreferences("CreateDocDetails",0)
               var filename=  sp.getString("filename","")as String
                var des =sp.getString("description","")as String
                var endstartdate =sp.getString("endstartdate","")as String
             var endexpdate  = sp.getString("endexpdate","")as String
            var  sidningduedate=    sp.getString("signingduedate","")as String
            var seqpar=    sp.getString("seqpara","")as String
            var reminddays =   sp.getString("reminddays","")?.toInt()
              var docid =  sp.getString("docid","")as String
                var totsign=List<Int>(1) {3}
                var authtype=List<Int>(1) {1}
                var token = this.getSharedPreferences("Token",0).getString("Token","").toString()
                Toast.makeText(this,documentshapemodel[0].signatureType.toString(),Toast.LENGTH_LONG).show()
           var createapi = RetrofitClient.getInstance().api as Api
                var createcall = createapi.create(
                    token, CreateDocRequest(
                        docid,
                        "samplepdf",
                        "pdfcreate",
                        ".pdf",des,
                        endstartdate,
                        endexpdate,
                        sidningduedate,
                        reminddays,
                        documentshapemodel as List<DocumentShapeModel>,
                        1,
                        signersid as List<String>,
                        null,
                        totsign as List<Int>,
                        4.092356,
                        -56.062161,
                        "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36",
                        "61.12.66.6",
                        authtype) )as Call<CreateDocResponse>

                createcall.enqueue(object : retrofit2.Callback<CreateDocResponse>{
                    override fun onFailure(call: Call<CreateDocResponse>, t: Throwable) {
                        Toast.makeText(this@Annotation2,"somethinf-g went wrong",Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<CreateDocResponse>, response: Response<CreateDocResponse>) {
                       if(response.isSuccessful){
                           Toast.makeText(this@Annotation2,"successfully created",Toast.LENGTH_LONG).show()

                       }else{
                           Toast.makeText(this@Annotation2,response.message().toString(),Toast.LENGTH_LONG).show()

                       }
                    }
                })


                }


            }

        return super.onOptionsItemSelected(item)
    }

}
