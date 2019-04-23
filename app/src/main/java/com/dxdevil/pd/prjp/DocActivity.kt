package com.dxdevil.pd.prjp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.AwOthers
import com.dxdevil.pd.prjp.Model.Completed
import com.dxdevil.pd.prjp.data.DocumentListAdapter
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document
import com.dxdevil.pd.prjp.Model.DueSoon
import com.dxdevil.pd.prjp.Model.Request.Document.ListOfDocument
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.ListOfDocumentResponse
import kotlinx.android.synthetic.main.activity_doc.*
import kotlinx.android.synthetic.main.row_doclist.*


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocActivity : AppCompatActivity() {
    private var adapter: DocumentListAdapter? = null
    private var documentList: MutableList<Document>? = null
    public  var extention: String? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc)



        val builder = android.app.AlertDialog.Builder(this)
        val dialogview = layoutInflater.inflate(R.layout.progress_dialog,null)
        val message = dialogview.findViewById<TextView>(R.id.progress_message)
        message.text="Loading..."
        builder.setView(dialogview)
        builder.setCancelable(false)
        val dialog=builder.create()
        dialog.show()



        mrecyclerView.layoutManager = LinearLayoutManager(this@DocActivity)
        mrecyclerView.adapter = adapter

        val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.N9w0jV/v+9PKjqTQVRprezt4+qqzzgZZjY68hHbb/VdLYi/P2x192in+N/puRDAp8zEKMMqdnOPVvgPE6C6bukPznNX+g+jkb5sv8FfeS7Q7UXY/QIHjeYLQJ4Tk0Aziq/VdzyrDQLOq20vUdEYJjwIBiq1pk+R/7xBYPpEEPI6lQtTxggOw72uJnYt/kwviC0i6ppoGmWZHU5sX4ZyNBnhSp5RPMYvQKENl5w3MdkGet6cb9MaMD55LM0Ytqy2DMHMqW4H1t4ql13hFw3c/1MhX/sNci8G6WNp1Bo6qnFwi+eu6ABRNaQ0C4lXKjy4K/9Mj9ZN6dqQLCWdpsbW6Bx1K46PitJsHNo6bqFVlUWyxRHNeIJKu655Ema646p9o62EeO4LDIrUhOJLPMpXXDwbdcgHAsLBpM/TMfOdQlaLsB2d6rOlkqlh0n8ForetQ+M19xiDH0C+YxrDeDlnY6v62VfXDwwayEVEgmjZv2SM/CKPoyQgHsYEh3tGXECqP9UVxmqL+jPbLhIrTlku4Z1LsZXmRVc/gEq1/NJPIU7DtF1PEkfIs4VUHuwGClKyyQ2GfWmecbn5RZ4WF4HZpOr8KUDo5TsLVee+4w0W75MRPSaQZVRBz/AaW5owhvQ1DsBzHmO01XfKxj5oXASQUaSOA6OoAyFgrA+JHMi3g10sjOVFPhiHSDyOPenpYcw7rrxB7pXpVEdnW1gen3oT5BSJ+PzMfFkt2sg0Mg4KRFMMG0/cG6wP5C1W2gKQT2hVENRxfTYeifEjLE0oTVot9g+xlczLrrvAjqJS9SuHk+nEk/Jp9TPyv6Aj/qPMtuy09koJ9fKSl0K35bGMMmwxZYVly4Se1dAPJ+YAIO7Ux6rhs7AuDezmqPIsKwdDB9C96.E9qpFP0Y7gSB_8vo4ejR6M2mWHxnhRaMJGUQP5NxdNM"
        val api = RetrofitClient.getInstance().api as Api
        val call = api.doclist(
            token, ListOfDocument(
                0,
                0,
                true,
                null,
                null,
                null,
                0,
                null,
                null,
                null
            )
        )as Call<ListOfDocumentResponse>

        try{
            call.enqueue(object : Callback<ListOfDocumentResponse> {
                override fun onFailure(call: Call<ListOfDocumentResponse>, t: Throwable) {
                    Toast.makeText(this@DocActivity, "Check your connection", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ListOfDocumentResponse>, response: Response<ListOfDocumentResponse>) {


                    if (response.isSuccessful) {

                        try {


                            adapter = DocumentListAdapter(response.body()!!.data[0].documents, this@DocActivity)
                            documentList= response.body()!!.data[0].documents


                            mrecyclerView.layoutManager = LinearLayoutManager(this@DocActivity)
                            mrecyclerView.adapter = DocumentListAdapter(response.body()!!.data[0].documents, this@DocActivity)
                            adapter!!.notifyDataSetChanged()

                            dialog.dismiss()

                        }catch (e:Exception)
                        {
                            dialog.dismiss()
                            Toast.makeText(this@DocActivity, e.message,Toast.LENGTH_LONG).show()
                        }

                    } else {
                        dialog.dismiss()
                        Toast.makeText(this@DocActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }catch (e:Exception){
            Toast.makeText(this@DocActivity,"Excetion",Toast.LENGTH_SHORT).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {

            R.id.menu_awsign -> {
                Toast.makeText(applicationContext, "Awaiting my sign", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, DocActivity::class.java))

                return true
            }

            R.id.menu_awOthers -> {
                Toast.makeText(applicationContext, "Awaiting Others", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(applicationContext, AwOthers::class.java))
                startActivity(Intent(applicationContext,AwOthers::class.java))
                return true
            }

            R.id.menu_duesoon -> {
                Toast.makeText(applicationContext, "Due Soon", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, DueSoon::class.java))

                return true
            }

            R.id.menu_completed -> {
                Toast.makeText(applicationContext, "Completed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, Completed::class.java))

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
