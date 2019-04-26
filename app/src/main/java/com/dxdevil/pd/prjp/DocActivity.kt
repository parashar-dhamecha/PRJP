package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document
import com.dxdevil.pd.prjp.Model.Request.Document.ListOfDocument
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.ListOfDocumentResponse
import com.dxdevil.pd.prjp.data.*
import kotlinx.android.synthetic.main.activity_doc.*


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocActivity : AppCompatActivity() {
    var currentPage=0
    var totalPages:Int=0

   // var cpage:Int=0
    private var adapter: AllDocumentsAdapter? = null
    private lateinit var documentList: ArrayList<Document>

    lateinit var alldocs : List<Document>
    val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.N9w0jV/v+9PKjqTQVRpre3RUiN3wdIdsrq1N180MSCy6TWjRC69b0sK/LNMIiAMehYWjcQhhc3gpadVlW//A/OjbgREERhooiUtBj3Qc3bispZ0uzeoYMwXx1SAULv/lwD+6crG4AJf/zBCPXQCvwyRlI8mw9rwmLCdAJon2bxLr/5MIZwisAOPvC9BnJlAW7t38vIkXhXAB3cK7BIZM+jTIoHfZGQXHm2GeRgGOCTTq3MigXtbb3utVCNCZ1EuRkbd6G24UD8/0HJ+yTYJwJiNwcj5Q6t59kzyGezMGcVrMVde2d8kEOe5XnDkv/Wn5JPXqud+6WL4Z/hbvo6IJpUnatlNZD1p7fFHFecG6ChWnpVCNov+BPxL/k8+ROCb1zSMHPTr4c7TD2PNtp8LwDFQysvGbPThyD9ZFd/3ZB8m1EtXbOImhDNRxEUHGHnwrTObIY1gUycJ3gwCz8HsCGxY+62EnBA1rxG0TqERjQzqjaGeBqR9f4okL5KE+L5v/aGoUC4+XLEYsdsCjdn0VHtC7JEOK3NWcOIeuZWwEqLmJrZ7mF71HBjtjHiGIgzy5pVetEl3jEllw0F3Q99nDAYZIA1FQEeiXsUmLjDiHv/JOA2Ace7Oxybofke7dJc+X5hXJvSc6j5PgdwRA9+BP27ohQdBXADrh45RxeNbzdIVIlhRJ7Qqu+yBFucqQy5whh2ryEU0QEpDBOCeVIUhIceiUNB/oFf65TqohBfR+E8szzLkNXV1em0k6e5JZXXGBYQDXAng+3ps9mA4eRQ/hH2fR2hmjhOT+opPCg/DXt6L8tUcob59ecGerJhlaCB5/bHjXlDuouUnfC84PXG+ropSrbVD0RoSjE6pdrmnQCR+AFnjMcKDVL8UhQ/DoNlxE.nqkuagfxdK5CyM6Fv7ypOZTgOrT16altAWi1nQEjIKI"

   // @SuppressLint("InflateParams")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc)

        apiCalling(0, 0, token)

       // page_number.setText(cpage)

            button_previous.isClickable=false

        button_next.setOnClickListener {
            if(totalPages==1||currentPage>=totalPages.inc())
                Toast.makeText(this@DocActivity,"There are no more Documents",Toast.LENGTH_SHORT).show()
            else{
                currentPage +=1
                apiCalling(0,currentPage,token)}

        }
        button_previous.setOnClickListener {
            currentPage -= 1
            apiCalling(0,currentPage,token)
        }




    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {

            R.id.allDocuments -> {

                Toast.makeText(applicationContext, "All documents", Toast.LENGTH_SHORT).show()
                apiCalling(0,currentPage,token)
                return true
            }

            R.id.menu_awaitingMySign -> {

                Toast.makeText(applicationContext, "Awaiting My sign", Toast.LENGTH_SHORT).show()
                apiCalling(0,currentPage,token)
                return true
            }

            R.id.menu_awaitingOthers -> {

                Toast.makeText(applicationContext, "Awaiting Others", Toast.LENGTH_SHORT).show()
                apiCalling(3,currentPage,token)
                return true
            }

            R.id.menu_completed -> {

                Toast.makeText(applicationContext, "Completed", Toast.LENGTH_SHORT).show()
                apiCalling(2,currentPage,token)
                return true
            }


            R.id.menu_duesoon -> {

                Toast.makeText(applicationContext, "Due Soon", Toast.LENGTH_SHORT).show()
                apiCalling(6,currentPage,token)
                return  true
            }

            R.id.menu_Declined -> {
                Toast.makeText(applicationContext, "Declined", Toast.LENGTH_SHORT).show()
                apiCalling(7,currentPage,token)
                return  true
              }
            }
        return super.onOptionsItemSelected(item)
        }



    @SuppressLint("InflateParams")
    private fun apiCalling(status:Int, currentpage:Int, token:String ){



        val builder = android.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.progress_message)

        message.text = getString(R.string.Loading)
        builder.setView(dialogView)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()

        mrecyclerView.layoutManager = LinearLayoutManager(this@DocActivity)
        mrecyclerView.adapter = adapter


        val api = RetrofitClient.getInstance().api as Api

        val call = api.doclist(
            token, ListOfDocument(
                status,
                currentpage,
                true,
                null,
                null,
                null,
                0,
                null,
                null,
                null
            )
        ) as Call<ListOfDocumentResponse>

        try {
            call.enqueue(object : Callback<ListOfDocumentResponse> {
                override fun onFailure(call: Call<ListOfDocumentResponse>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(this@DocActivity, "Check your connection", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ListOfDocumentResponse>, response: Response<ListOfDocumentResponse>) {

                    if (response.isSuccessful) {
                        try {
                            adapter = AllDocumentsAdapter(response.body()!!.data[0].documents, this@DocActivity )
                            documentList = response.body()!!.data[0].documents as ArrayList<Document>

                            mrecyclerView.layoutManager = LinearLayoutManager(this@DocActivity)
                            mrecyclerView.adapter = AllDocumentsAdapter(response.body()!!.data[0].documents, this@DocActivity)

                            alldocs=response.body()!!.data[0].documents
                            totalPages=response.body()!!.data[0].totalPages
                            //cpage=response.body()!!.data[0].currentPage
                            adapter!!.notifyDataSetChanged()

                            dialog.dismiss()

                        } catch (e: Exception) {
                            dialog.dismiss()
                            Toast.makeText(this@DocActivity, e.message, Toast.LENGTH_LONG).show()
                        }

                    } else {
                        dialog.dismiss()
                        Toast.makeText(this@DocActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            Toast.makeText(this@DocActivity, "Excetion", Toast.LENGTH_SHORT).show()
        }
    }
}
