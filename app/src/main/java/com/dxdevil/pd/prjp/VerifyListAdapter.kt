package com.dxdevil.pd.prjp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.Model.Response.Verify.List.DocumentList

class VerifyListAdapter(var list: ArrayList<DocumentList>,
                        var context: Context): RecyclerView.Adapter<VerifyListAdapter.ViewHolder>() {

    var sp = context!!.getSharedPreferences("userid", 0) as SharedPreferences
    var ed = sp.edit()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.docname.text=list[position].name
        holder.docstatus.setTextColor(Color.rgb(128, 0, 0))

        holder.docname.setTextColor(Color.rgb(20, 3, 54))
        if(list[position].extension==".pdf")
            holder.fileimage.setImageResource(R.drawable.pdf3)
        if(list[position].extension==".docx"|| list[position].extension==".doc")
            holder.fileimage.setImageResource(R.drawable.doc4)
        if(list[position].extension==".xlsx"|| list[position].extension==".xls")
            holder.fileimage.setImageResource(R.drawable.excel)
        if(list[position].extension==".pptx"|| list[position].extension==".ppt")
            holder.fileimage.setImageResource(R.drawable.ppt2)

        holder.time.text=list[position].creationTime.toString()


      holder.run { cardfile.setOnClickListener{

             var s = list[position].id
          ed.putString("userid",s)
          ed.commit()
          val intent = Intent(context, VerifyDocumentDetail::class.java)

          context.startActivity(intent)

      }

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.verifyrow_list, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var docname: TextView = itemView.findViewById(R.id.txt_doc) as TextView
        var docstatus: TextView =itemView.findViewById(R.id.doc_status) as TextView
        var fileimage: ImageView = itemView.findViewById(R.id.file_image) as ImageView
        var time=itemView.findViewById(R.id.TVtime) as TextView
       var cardfile: CardView =itemView.findViewById(R.id.card_view_file) as CardView

    }
}


