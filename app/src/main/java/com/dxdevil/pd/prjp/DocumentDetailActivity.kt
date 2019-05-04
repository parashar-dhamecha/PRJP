package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    private var docId:String?=null
    @SuppressLint("InflateParams")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_detail)

        val intent = intent
         docId = intent.getStringExtra("doc")

        tvNo_observers.visibility=View.GONE
        tvNot_notarized.visibility=View.GONE

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


        btn_Preview.setOnClickListener {
            val intent1 = Intent(this@DocumentDetailActivity, PreviewActivity::class.java)
            intent1.putExtra("doc", docId)
            this.startActivity(intent1)
        }
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

                          if(response.body()!!.data[0].notarization.isNotarized==false){
                                notarized_on.visibility=View.GONE
                                time_date.visibility=View.GONE
                                transaction_hash.visibility=View.GONE
                                transaction_hash_value.visibility=View.GONE
                                button2.visibility=View.GONE
                                tvNot_notarized.visibility=View.VISIBLE
                          }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.docdetails_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {

            R.id.preview -> {
                val intent = Intent(this@DocumentDetailActivity, PreviewActivity::class.java)
                intent.putExtra("doc", docId)
                this.startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
