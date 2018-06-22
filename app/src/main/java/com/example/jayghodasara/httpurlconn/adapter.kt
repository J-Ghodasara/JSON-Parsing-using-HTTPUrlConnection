package com.example.jayghodasara.httpurlconn

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class adapter(var context: Context, var arrayList: ArrayList<pojo>) : RecyclerView.Adapter<adapter.viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return viewholder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var p: pojo = arrayList[position]

        var name = p.name
        var id = p.id
        var email = p.email
        var address = p.address
        var gender = p.gender
        var phone = p.mobile

        holder.Id.text = id
        holder.Name.text = name
        holder.Email.text = email
        holder.Address.text = address
        holder.Gender.text = gender
        holder.mobile.text = phone
    }


    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        var Id: TextView = view.findViewById(R.id.id)
        var Name: TextView = view.findViewById(R.id.name)
        var Email: TextView = view.findViewById(R.id.email)
        var Address: TextView = view.findViewById(R.id.address)
        var Gender: TextView = view.findViewById(R.id.gender)
        var mobile: TextView = view.findViewById(R.id.phone)

    }
}