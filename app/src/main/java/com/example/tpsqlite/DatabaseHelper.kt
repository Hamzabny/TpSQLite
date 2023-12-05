package com.example.tpsqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "student_db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "students"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COLUMN_GRADE = "grade"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME " +
                "(COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "COLUMN_NAME TEXT, " +
                "COLUMN_AGE INTEGER, " +
                "COLUMN_GRADE TEXT)"

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun deleteStudent(studentId: Long): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(studentId.toString()))
    }
    fun insertStudent(student: Student): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, student.name)
        values.put(COLUMN_AGE, student.age)
        values.put(COLUMN_GRADE, student.grade)

        return db.insert(TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun getAllStudents(): List<Student> {
        val students = mutableListOf<Student>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE))
                val grade = cursor.getString(cursor.getColumnIndex(COLUMN_GRADE))

                val student = Student(id, name, age, grade)
                students.add(student)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return students
    }
}
