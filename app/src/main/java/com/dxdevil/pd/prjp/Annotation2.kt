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
import kotlinx.android.synthetic.main.activity_annotation2.*
import kotlinx.android.synthetic.main.activity_uploadfile.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


@Suppress("ImplicitThis", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class Annotation2 : AppCompatActivity(),View.OnTouchListener{

    lateinit var viewarr: ArrayList<ImageView>
    lateinit var view :ImageView
     var viewcount: Int = 0
    lateinit var root: ViewGroup
    private var _xDelta: Int = 0
    private var _yDelta: Int = 0
    var currentpage=1
     var selsigners : ArrayList<String>? = ArrayList()
    var signersid : ArrayList<String>? = ArrayList()
    var signuserdet : ArrayList<DocumentShapeModel> = ArrayList()



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
                 var c =selsigners!!.indexOf(signerspinner.selectedItem)
                 signuserdet[viewcount].userId = (signersid as java.util.ArrayList<String>?)!![c]
                 signuserdet[viewcount].p=currentpage
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
        setLayoutsize(300,130)
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
                signuserdet[view.id].x=view.x as Int?
                signuserdet[view.id].y=view.y as Int?
                var rw= view.width/1.4
                var rh:Double = (view.height/2).toDouble()
                signuserdet[view.id].xPercentage=(((view.x*100)/rw)*root.width)/100
                signuserdet[view.id].yPercentage=(((view.y*100)/rh)*root.width)/100
                signuserdet[view.id].w=view.width
                signuserdet[view.id].h=view.height
                signuserdet[view.id].wPercentage=(100/rw)*100
                signuserdet[view.id].hPercentage=(100/rh)*100
                signuserdet[view.id].ratio="0.612903225806452"
                signuserdet[view.id].isAnnotation=true
                signuserdet[view.id].signatureType="ESignature"


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
            var reminddays =   sp.getString("reminddays","")as Int
              var docid =  sp.getString("docid","")as String


            var call = RetrofitClient.getInstance().api.create(
                CreateDocRequest(docid, filename,filename,".docx",des,endstartdate,endexpdate,sidningduedate,reminddays,signuserdet,null,null,null,null,null,null,null,null,null))
            call.enqueue(object : Callback, retrofit2.Callback<RequestBody> {
                override fun onFailure(call: Call<RequestBody>, t: Throwable) {
                    Toast.makeText(this@Annotation2,"something went wrong",Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<RequestBody>, response: Response<RequestBody>) {
                    if(response.isSuccessful){
                        Toast.makeText(this@Annotation2,"successfully uploaded",Toast.LENGTH_LONG).show()
                    }
                    else {
                        Toast.makeText(this@Annotation2,"error",Toast.LENGTH_LONG).show()
                    }
                }

            })


            }
        }
        return super.onOptionsItemSelected(item)
    }

}
