package com.dxdevil.pd.prjp.data

import android.content.Context
import android.content.Intent
import android.graphics.Color.rgb
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.DocumentDetailActivity
import com.dxdevil.pd.prjp.R
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.Document


class AllDocumentsAdapter(
    val list: List<Document>,
    val context:Context):
    RecyclerView.Adapter<AllDocumentsAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_doclist, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.docname.text=list[position].name
        holder.docname.setTextColor(rgb(20,3,54))

        if(list[position].extension==".pdf")
            holder.fileimage.setImageResource(R.drawable.pdf3)
        if(list[position].extension==".docx"|| list[position].extension==".doc")
            holder.fileimage.setImageResource(R.drawable.doc4)
        if(list[position].extension==".xlsx"|| list[position].extension==".xls")
            holder.fileimage.setImageResource(R.drawable.excel)
        if(list[position].extension==".pptx"|| list[position].extension==".ppt")
            holder.fileimage.setImageResource(R.drawable.ppt2)

        if(list[position].documentStatusForUser==0) {
            holder.docstatus.text=context.getString(R.string.awaiting)
            holder.docstatus.setTextColor(rgb(128,0,0))
        }
        if(list[position].documentStatusForUser==3) {
            holder.docstatus.text=context.getString(R.string.awatingothers)
            holder.docstatus.setTextColor(rgb(255,174,66))
        }
        if(list[position].documentStatusForUser==2) {
            holder.docstatus.text=context.getString(R.string.completed)
            holder.docstatus.setTextColor(rgb(53,117,55))
        }
        if(list[position].documentStatusForUser==6) {
            holder.docstatus.text=context.getString(R.string.signingdue)
            holder.docstatus.setTextColor(rgb(105,105,105))
        }
        if(list[position].documentStatusForUser==7) {
            holder.docstatus.text=context.getString(R.string.Declined)
            holder.docstatus.setTextColor(rgb(255,51,51))
        }

        holder.run { cardfile.setOnClickListener{
            val intent = Intent(context, DocumentDetailActivity::class.java)

            intent.putExtra("doc", list[position].id)
            //intent.putExtra("samplename", "abd")
            context.startActivity(intent)
        }
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var docname:TextView = itemView.findViewById(R.id.txt_doc) as TextView
            var docstatus:TextView=itemView.findViewById(R.id.doc_status) as TextView
            var fileimage:ImageView = itemView.findViewById(R.id.file_image) as ImageView
            var cardfile:CardView=itemView.findViewById(R.id.card_view_file) as CardView
    }

}


