package com.dxdevil.pd.prjp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.R
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document




class DocumentListAdapter(
    val list: List<Document>,
    val context:Context):
    RecyclerView.Adapter<DocumentListAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_doclist, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.docname.text = list.get(position).name
        list.get(position).extension
        if(list.get(position).extension==".pdf")
            holder.fileimage.setImageResource(R.drawable.pdf3)
        if(list.get(position).extension==".docx"||list.get(position).extension=="doc")
            holder.fileimage.setImageResource(R.drawable.doc)
        if(list.get(position).extension==".xlsx"||list.get(position).extension==".xls")
            holder.fileimage.setImageResource(R.drawable.excel)
        if(list.get(position).extension==".pptx"||list.get(position).extension==".ppt")
            holder.fileimage.setImageResource(R.drawable.ppt)
        
        if(list[position].documentStatusForUser==0) {
            holder.docstatus.text=context.getString(R.string.awaiting)
        }
        if(list[position].documentStatusForUser==3) {
            holder.docstatus.text=context.getString(R.string.awatingothers)
        }
        if(list[position].documentStatusForUser==2) {
            holder.docstatus.text=context.getString(R.string.completed)
        }
        if(list[position].documentStatusForUser==6) {
            holder.docstatus.text=context.getString(R.string.signingdue)
        }
    }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var docname:TextView = itemView.findViewById(R.id.txt_doc) as TextView
            var docstatus:TextView=itemView.findViewById(R.id.doc_status) as TextView
            var fileimage:ImageView = itemView.findViewById(R.id.file_image) as ImageView

    }

}


