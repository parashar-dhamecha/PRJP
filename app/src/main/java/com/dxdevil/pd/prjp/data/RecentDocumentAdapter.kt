package com.dxdevil.pd.prjp.data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.DocumentDetailActivity
import com.dxdevil.pd.prjp.R
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document


class RecentDocumentAdapter(
    val list: List<Document>,
    val context:Context):
    RecyclerView.Adapter<RecentDocumentAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recent_doc_recycler, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.docname.text=list[position].name
        if(list[position].extension==".pdf")
            holder.fileimage.setImageResource(R.drawable.icon_file_pdf)
        if(list[position].extension==".docx"|| list[position].extension==".doc")
            holder.fileimage.setImageResource(R.drawable.icon_file_doc)
        if(list[position].extension==".xlsx"|| list[position].extension==".xls")
            holder.fileimage.setImageResource(R.drawable.excel_64)
        if(list[position].extension==".pptx"|| list[position].extension==".ppt")
            holder.fileimage.setImageResource(R.drawable.icon_file_ppt)

        holder.parentLayout.setOnClickListener {
            val s = list[position].id
            val intent = Intent(context, DocumentDetailActivity::class.java)
            intent.putExtra("Source" ,"Dashboard")
            intent.putExtra("Docid",s)
            context.startActivity(intent)
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var docname:TextView = itemView.findViewById(R.id.doc_name_recent) as TextView
        var fileimage:ImageView = itemView.findViewById(R.id.doc_image_recent) as ImageView
        var parentLayout = itemView.findViewById(R.id.parent_layout) as ConstraintLayout

    }

}


