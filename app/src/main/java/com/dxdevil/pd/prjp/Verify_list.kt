package com.dxdevil.pd.prjp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dxdevil.pd.prjp.Model.Response.Verify.VerifyDetails.DocumentDetail

class Verify_list : AppCompatActivity() {

    private var adapter: VerifyListAdapter? = null
    private lateinit var documentList: ArrayList<DocumentDetail>
    lateinit var alldocs : List<DocumentDetail>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_list)
    }
}
