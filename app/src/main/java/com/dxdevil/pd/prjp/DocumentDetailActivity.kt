package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.Response.Document.DocDetails.DocDetailsResponse
import com.dxdevil.pd.prjp.Model.Response.Document.DocDetails.Observer
import com.dxdevil.pd.prjp.Model.Response.Document.DocDetails.Signer
import com.dxdevil.pd.prjp.data.ObserversAdapter
import com.dxdevil.pd.prjp.data.SignersAdapter
import kotlinx.android.synthetic.main.activity_document_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocumentDetailActivity : AppCompatActivity() {

    var token: String? = null
    lateinit  var signerlist :List<Signer>
    lateinit  var observerslist :List<Observer>

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_detail)

//        var st = getSharedPreferences("DocId", 0) as SharedPreferences
//        var docId=st.getString("DocId","")
        val intent = getIntent();
        val docId:String? = intent.getStringExtra("doc")
        tvNo_observers.visibility=View.GONE

        val builder = android.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.progress_message)
        message.text = getString(R.string.fetch)
        builder.setView(dialogView)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()



        setTitle(R.string.Details)

        token=getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")


        val api = RetrofitClient.getInstance().api as Api

        val call = api.docdetails(
            token,docId

        )as Call<DocDetailsResponse>

        try{
          call.enqueue(object : Callback<DocDetailsResponse> {
            override fun onFailure(call: Call<DocDetailsResponse>, t: Throwable) {
                dialog.dismiss()
               Toast.makeText(this@DocumentDetailActivity,"Something went wrong.",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DocDetailsResponse>, response: Response<DocDetailsResponse>) {

                if(response.isSuccessful) {

                    if (response.body()!!.data[0].documentDetail.extension == ".pdf")
                            doc_image.setImageResource(R.drawable.pdf3)
                        if (response.body()!!.data[0].documentDetail.extension == ".doc" || response.body()!!.data[0].documentDetail.extension == ".docx")
                            doc_image.setImageResource(R.drawable.doc4)
                        if (response.body()!!.data[0].documentDetail.extension == ".ppt" || response.body()!!.data[0].documentDetail.extension == ".pptx")
                            doc_image.setImageResource(R.drawable.ppt2)
                        if (response.body()!!.data[0].documentDetail.extension == ".xls" || response.body()!!.data[0].documentDetail.extension == ".xlsx")
                            doc_image.setImageResource(R.drawable.excel)

                        tvDoc_name.text = response.body()!!.data[0].documentDetail.name
                        tvUploaded_by_value.text = response.body()!!.data[0].documentDetail.uploadedBy
                        tvDocument_Hash_value.text = response.body()!!.data[0].documentDetail.documentFileHash

                            signerlist = response.body()!!.data[0].signers
                            observerslist = response.body()!!.data[0].observers

                            val layoutManager = LinearLayoutManager(applicationContext)
                            val layoutManager2 = LinearLayoutManager(applicationContext)
                            signer_recyclerview.layoutManager = layoutManager
                            signer_recyclerview.adapter = SignersAdapter(signerlist, this@DocumentDetailActivity)
                            observer_recyclerview.layoutManager = layoutManager2
                            observer_recyclerview.adapter = ObserversAdapter(observerslist, this@DocumentDetailActivity)

                            time_date.text = response.body()!!.data[0].notarization.notarizedOn

                            if(response.body()!!.data[0].observers.size==0){
                                observer_recyclerview.visibility=View.GONE
                                tvNo_observers.visibility=View.VISIBLE}

                            if(response.body()!!.data[0].notarization.txHash==null)
                                transaction_hash.text=response.body()!!.data[0].notarization.notarizeMessage
                            else
                            transaction_hash.text = response.body()!!.data[0].notarization.txHash.toString()

                            dialog.dismiss()


                }
                else {
                    dialog.dismiss()
                    Toast.makeText(this@DocumentDetailActivity, "failure", Toast.LENGTH_SHORT).show()
                }

               }


        }) }catch (e:Exception){
                  Toast.makeText(this@DocumentDetailActivity,"Exception:"+e.message,Toast.LENGTH_SHORT).show()
              }
    }
}
