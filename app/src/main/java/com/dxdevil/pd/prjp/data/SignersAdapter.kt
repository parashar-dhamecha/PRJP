package com.dxdevil.pd.prjp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.Model.Response.Document.DocDetails.Signer
import com.dxdevil.pd.prjp.R


class SignersAdapter(
    var list: List<Signer>, var context:Context): RecyclerView.Adapter<SignersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.signers, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.shortname.text=list[position].profileShortName
        holder.signername.text=list[position].firstName
        holder.btnSigner.text=list[position].signButtonText


        if(list[position].signButtonText=="Sign"||list[position].isSigned==true)
            holder.btnSigner.visibility=View.GONE
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var shortname: TextView = itemView.findViewById(R.id.tvshort_name) as TextView
        var signername: TextView = itemView.findViewById(R.id.tvSigner_name) as TextView
        var btnSigner: Button = itemView.findViewById(R.id.btnSigner) as Button

    }

}
