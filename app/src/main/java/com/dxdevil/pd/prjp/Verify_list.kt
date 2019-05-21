package com.dxdevil.pd.prjp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.Request.VerifyDocList
import com.dxdevil.pd.prjp.Model.Response.Verify.List.DocumentList
import com.dxdevil.pd.prjp.Model.Response.Verify.List.VerifyListResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_verify_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Verify_list : AppCompatActivity() {

    private var adapter: VerifyListAdapter? = null
    private lateinit var documentList: ArrayList<DocumentList>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_list)
        img_No_doc.visibility = View.GONE
        tvNo_doc.visibility = View.GONE

        var f = intent.getStringExtra("filehash")
        var f1 = intent.getStringExtra("Transhash")


        val builder = android.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.progress_message)

        message.text = getString(R.string.Loading)
        builder.setView(dialogView)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()

        var token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")
        swipeRefreshDocuments!!.isRefreshing = true
        swipeRefreshDocuments.visibility = View.VISIBLE


        val api1 = RetrofitClient.getInstance()!!.api as Api
        var call1 = api1.verifylist(token, VerifyDocList(f, 0, "", "")) as Call<VerifyListResponse>
        call1.enqueue(object : Callback<VerifyListResponse> {
            override fun onFailure(call: Call<VerifyListResponse>, t: Throwable) {
                dialog.dismiss()

                img_No_doc.visibility = View.VISIBLE
                tvNo_doc.visibility = View.VISIBLE


                Snackbar.make(findViewById(R.id.coordinatorLayout),"Check your internet connection", Snackbar.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<VerifyListResponse>, response: Response<VerifyListResponse>) {
                swipeRefreshDocuments!!.isRefreshing = false


                if (response.isSuccessful) {

                    img_No_doc.visibility = View.GONE
                    tvNo_doc.visibility = View.GONE


                    dialog.dismiss()


                    print("response" + response.body().toString())
                    var documentList = response.body()!!.data[0].documentList as List<DocumentList>
                    println("document list size" + documentList.size)
                    adapter = VerifyListAdapter(documentList as ArrayList<DocumentList>, this@Verify_list)

                    mrecyclerView!!.layoutManager = LinearLayoutManager(this@Verify_list)
                    mrecyclerView!!.adapter = adapter
                } else {
                    Snackbar.make(findViewById(R.id.coordinatorLayout),  response.message().toString(), Snackbar.LENGTH_LONG).show()

                }


            }
        })


        var token1 = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")
        swipeRefreshDocuments!!.isRefreshing = true
        swipeRefreshDocuments.visibility = View.VISIBLE


        val api = RetrofitClient.getInstance()!!.api as Api
        var calll = api.verifylist(token1, VerifyDocList("", 0, f1, "")) as Call<VerifyListResponse>
        calll.enqueue(object : Callback<VerifyListResponse> {
            override fun onFailure(call: Call<VerifyListResponse>, t: Throwable) {
                dialog.dismiss()
                swipeRefreshDocuments!!.isRefreshing = false

                Snackbar.make(findViewById(R.id.coordinatorLayout),"Check your internet connection", Snackbar.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<VerifyListResponse>, response: Response<VerifyListResponse>) {
                swipeRefreshDocuments!!.isRefreshing = false


                if (response.isSuccessful) {

                    img_No_doc.visibility = View.GONE
                    tvNo_doc.visibility = View.GONE


                    dialog.dismiss()




                    print("response" + response.body().toString())
                    var documentList = response.body()!!.data[0].documentList as List<DocumentList>
                    println("document list size" + documentList.size)
                    adapter = VerifyListAdapter(documentList as ArrayList<DocumentList>, this@Verify_list)

                    mrecyclerView!!.layoutManager = LinearLayoutManager(this@Verify_list)
                    mrecyclerView!!.adapter = adapter
                } else {
                    dialog.dismiss()
                    Snackbar.make(findViewById(R.id.coordinatorLayout),  response.message().toString(), Snackbar.LENGTH_LONG).show()

                }


            } })


        }
    }
