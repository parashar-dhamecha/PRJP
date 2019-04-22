package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat.startActivity






class ContactsAdapter(private var context: Context, var Con: ArrayList<ContactModel>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contactsadapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Con.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pro.setImageResource(Con[position].photo)
        holder.name.text = Con[position].name

        holder.delbutton.setOnClickListener{

            print("clicked")

        }

        holder.editbutton.setOnClickListener{

            context.startActivity(Intent(context, UpdateContact::class.java))

            //val intent = Intent(this, UpdateContact::class.java)
            //startActivity(intent)



        }
        holder.del.setOnClickListener{

            Con.removeAt(position)
            notifyItemRemoved(position)



        }

       /* fun setContactsListItems(Con: ArrayList<ContactModel>)
        {
            this.Con=Con
            notifyDataSetChanged()
        }*/


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name) as TextView
        var pro: ImageView = itemView.findViewById(R.id.pro) as ImageView
        var del: Button = itemView.findViewById(R.id.del) as Button
       // @SuppressLint("WrongViewCast")
        var delbutton:ImageButton= itemView.findViewById(R.id.delbutton) as ImageButton

        var editbutton:ImageButton= itemView.findViewById(R.id.editbutton) as ImageButton




    }
}









