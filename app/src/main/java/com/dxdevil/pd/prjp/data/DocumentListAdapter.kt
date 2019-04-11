package com.dxdevil.pd.prjp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.R
import com.dxdevil.pd.prjp.Model.Document
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Datum
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.ListOfDocumentResponse

//class DocumentListAdapter(private val list:ArrayList<Document>, private val context:Context) :
//    RecyclerView.Adapter<DocumentListAdapter.ViewHolder>(){
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.row_doclist,parent,false)
//        return ViewHolder(view)
//    }
//
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder?.bindItem(list[position])
//
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bindItem(document: Document){
//            var docname:TextView = itemView.findViewById(R.id.txt_doc) as TextView
//
//            docname.text = document.docname
//        }
//    }
//
//}

class DocumentListAdapter(
    val list: List<com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document>,
    val context:Context):
    RecyclerView.Adapter<DocumentListAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return 10
    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_doclist,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])

    }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItem(document : com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document){
                var docname:TextView = itemView.findViewById(R.id.txt_doc) as TextView
                docname.text=document.name

        }
    }

}

