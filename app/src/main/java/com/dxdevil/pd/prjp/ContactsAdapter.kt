package com.dxdevil.pd.prjp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView




class ContactsAdapter(private val context: Context, private val Con: ArrayList<ContactModel>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.contactsadapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Con.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pro.setImageResource(Con[position].photo)
        holder.name.text = Con[position].name
        holder.del.setOnClickListener{

            Con.removeAt(position)
            notifyItemRemoved(position)



        }


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name) as TextView
        var pro: ImageView = itemView.findViewById(R.id.pro) as ImageView
        var del: Button = itemView.findViewById(R.id.del) as Button

    }
}









