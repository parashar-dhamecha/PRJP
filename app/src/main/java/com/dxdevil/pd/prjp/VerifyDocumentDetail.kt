package com.dxdevil.pd.prjp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.Response.Document.DocDetails.Observer
import com.dxdevil.pd.prjp.Model.Response.Document.DocDetails.Signer
import com.dxdevil.pd.prjp.data.ObserversAdapter
import com.dxdevil.pd.prjp.data.SignersAdapter
import kotlinx.android.synthetic.main.activity_verify_document_detail.*

class VerifyDocumentDetail : AppCompatActivity() {

    lateinit  var signerlist :List<Signer>
    lateinit  var observerslist :List<Observer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_document_detail)

//        val layoutManager = LinearLayoutManager(applicationContext)
//        val layoutManager2 = LinearLayoutManager(applicationContext)
//        signer_recyclerview_verify.layoutManager = layoutManager
//        signer_recyclerview_verify.adapter = SignersAdapter(signerlist, this@VerifyDocumentDetail)
//        observer_recyclerview_verify.layoutManager = layoutManager2
//        observer_recyclerview_verify.adapter = ObserversAdapter(observerslist, this@VerifyDocumentDetail)


    }



}
