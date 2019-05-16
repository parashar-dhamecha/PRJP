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


class VerifySignersAdapter(
    var list: List<com.dxdevil.pd.prjp.Model.Response.Verify.VerifyDetails.Signer>, var context:Context): RecyclerView.Adapter<VerifySignersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.verify_signers, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.shortname.text=list[position].profileShortName
        holder.signername.text=list[position].firstName
         }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var shortname: TextView = itemView.findViewById(R.id.tvshort_name) as TextView
        var signername: TextView = itemView.findViewById(R.id.tvSigner_name) as TextView

    }

}
