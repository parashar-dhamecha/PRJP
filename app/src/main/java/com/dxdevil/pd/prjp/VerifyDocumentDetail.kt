package com.dxdevil.pd.prjp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.Response.Verify.VerifyDetails.VerifyDetailsResponse
import com.dxdevil.pd.prjp.data.VerifyObserversAdapter
import com.dxdevil.pd.prjp.data.VerifySignersAdapter
import kotlinx.android.synthetic.main.activity_verify_document_detail.*
import retrofit2.Call
import retrofit2.Callback
import com.dxdevil.pd.prjp.Model.Response.Verify.VerifyDetails.*
import retrofit2.Response

class VerifyDocumentDetail : AppCompatActivity() {

    lateinit  var signerlist :List<Signer>
    lateinit  var observerslist :List<Observer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_document_detail)

        var st = getSharedPreferences("userid", 0) as SharedPreferences



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

        var token=getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")
        val api = RetrofitClient.getInstance().api as Api

        var call1 = api.getVerificationDetails(token,st.getString("userid", "")) as Call<VerifyDetailsResponse>
        call1.enqueue(object : Callback<VerifyDetailsResponse> {
            override fun onFailure(call: Call<VerifyDetailsResponse>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@VerifyDocumentDetail, "Check your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<VerifyDetailsResponse>, response: Response<VerifyDetailsResponse>) {
                if(response.isSuccessful)
                {

                    dialog.dismiss()
                    Toast.makeText(this@VerifyDocumentDetail, "Success", Toast.LENGTH_LONG).show()
                    if (response.body()!!.data[0].documentDetail.extension == ".pdf")
                        img.setImageResource(R.drawable.pdf3)
                    if (response.body()!!.data[0].documentDetail.extension == ".doc" || response.body()!!.data[0].documentDetail.extension == ".docx")
                        img.setImageResource(R.drawable.doc4)
                    if (response.body()!!.data[0].documentDetail.extension == ".ppt" || response.body()!!.data[0].documentDetail.extension == ".pptx")
                        img.setImageResource(R.drawable.ppt2)
                    if (response.body()!!.data[0].documentDetail.extension == ".xls" || response.body()!!.data[0].documentDetail.extension == ".xlsx")
                        img.setImageResource(R.drawable.excel)


                    txt.text = response.body()!!.data[0].documentDetail.name


                    signerlist = response.body()!!.data[0].signers

                    observerslist = response.body()!!.data[0].observers



                    val layoutManager = LinearLayoutManager(applicationContext)
                    val layoutManager2 = LinearLayoutManager(applicationContext)
                    signer_recyclerview_verify.layoutManager = layoutManager
                    signer_recyclerview_verify.adapter = VerifySignersAdapter(signerlist, this@VerifyDocumentDetail)

                    observer_recyclerview_verify.layoutManager = layoutManager2
                    observer_recyclerview_verify.adapter = VerifyObserversAdapter(observerslist, this@VerifyDocumentDetail)
                    notarized_text.text = response.body()!!.data[0].notarization.notarizedOn
                    blocktext.text = response.body()!!.data[0].notarization.blockId as CharSequence?

                   // digi_text.text = response.body()!!.data[0].notarization.txHash.toString()
                    doc_Historytext.text = response.body()!!.data[0].documentHistory[0].historyText
                    if(response.body()!!.data[0].observers.size==0){
                        observer_recyclerview_verify.visibility=View.GONE
                        tvNo_observers.visibility=View.VISIBLE}







                }


           }})



}
}