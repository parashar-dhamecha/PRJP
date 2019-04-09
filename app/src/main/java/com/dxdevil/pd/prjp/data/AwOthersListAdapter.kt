package com.dxdevil.pd.prjp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.R
import com.dxdevil.pd.prjp.Model.AwOthers

class AwOthersListAdapter(private val list: ArrayList<AwOthers>, private val context: Context) :
    RecyclerView.Adapter<AwOthersListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_doclist, parent, false)
        return AwOthersListAdapter.ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bindItem(list[position])
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(document: AwOthers) {
            var docname: TextView = itemView.findViewById(R.id.txt_doc) as TextView
            var bt_sign: Button = itemView.findViewById(R.id.bt_sign) as Button
            //bt_sign.text("Remind")
           // bt_sign.setText("Remind")
            docname.text = document.docname
        }
    }

}
