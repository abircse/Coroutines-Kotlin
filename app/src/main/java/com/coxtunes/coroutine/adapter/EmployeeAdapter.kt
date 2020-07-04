package com.example.PixelLime.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coxtunes.coroutine.R
import com.coxtunes.coroutine.model.EmployeeData


class EmployeeAdapter(private val employeelist: List<EmployeeData>, val context: Context) :
        RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_items, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return employeelist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employeeData: EmployeeData = employeelist[position]
        holder.name.setText(employeeData.Name)
        holder.email.setText(employeeData.Position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById(R.id.text_name) as TextView
        val email = itemView.findViewById(R.id.text_email) as TextView
    }
}
