package com.dxdevil.pd.prjp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

 class ContactsAdapter( private val context: Context, private val Con: ArrayList<Int> ): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.contactsadapter, parent, false)
public class ContactsAdapter<Context>(private val list: ArrayList<ModelClass>,
                               private val context:Context) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.contactsadapter, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Con.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pro.setImageResource(Con[position])
        holder.name.setText(Con[position])
//       holder.del.setButton(Con[position].getImage_drawables())



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var name: TextView
            var pro: ImageView
  //          var del: Button

            init {

                name = itemView.findViewById(R.id.name) as TextView
                pro = itemView.findViewById(R.id.pro) as ImageView
    //            del = itemView.findViewById(R.id.del) as Button

            }
        }
    }










