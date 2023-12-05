package com.example.tpsqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudentAdapter(private val context: Context, private var students: List<Student>) : BaseAdapter() {
    override fun getCount(): Int {
        return students.size
    }

    override fun getItem(position: Int): Any {
        return students[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_student, parent, false)
            holder = ViewHolder()
            holder.tvName = view.findViewById(R.id.tvName)
            holder.tvAge = view.findViewById(R.id.tvAge)
            holder.tvGrade = view.findViewById(R.id.tvGrade)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val student = getItem(position) as Student
        holder.tvName.text = "Name: ${student.name}"
        holder.tvAge.text = "Age: ${student.age}"
        holder.tvGrade.text = "Grade: ${student.grade}"

        return view!!
    }

    fun updateData(updatedStudents: List<Student>) {
        students = updatedStudents
        notifyDataSetChanged()
    }
    private class ViewHolder {
        lateinit var tvName: TextView
        lateinit var tvAge: TextView
        lateinit var tvGrade: TextView
    }
}
