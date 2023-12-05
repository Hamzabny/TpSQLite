package com.example.tpsqlite
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etGrade = findViewById<EditText>(R.id.etGrade)
        val btnAddStudent = findViewById<Button>(R.id.btnAddStudent)
        val btnViewStudents = findViewById<Button>(R.id.btnViewStudents)
        dbHelper = DatabaseHelper(this)
        btnAddStudent.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()
            val grade = etGrade.text.toString()

            val student = Student(null, name, age, grade)
            dbHelper.insertStudent(student)

            etName.text.clear()
            etAge.text.clear()
            etGrade.text.clear()
        }

        btnViewStudents.setOnClickListener {
            val intent = Intent(this, ViewStudentsActivity::class.java)
            startActivity(intent)
        }
    }
}
