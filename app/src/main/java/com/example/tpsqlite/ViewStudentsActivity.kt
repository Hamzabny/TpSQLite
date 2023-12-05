package com.example.tpsqlite

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ViewStudentsActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_students)

        dbHelper = DatabaseHelper(this)
        listView = findViewById(R.id.listView)

        val students = dbHelper.getAllStudents()
        val adapter = StudentAdapter(this, students)
        listView.adapter = adapter

        // Ajoutez un écouteur d'événements pour la suppression lors du clic sur un élément de la liste
        listView.setOnItemClickListener { _, _, position, _ ->
            val studentToDelete = students[position]
            dbHelper.deleteStudent(studentToDelete.id ?: 0)

            // Rafraîchissez la liste après la suppression
            val updatedStudents = dbHelper.getAllStudents()
            adapter.updateData(updatedStudents)
        }
    }
}
