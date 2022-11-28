package com.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "studentTable")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String studentName;
    private String studentLastName;

    @ColumnInfo(name = "Type")
    private String studentType;

    public Student(String studentName, String studentLastName, String studentType) {
        this.studentName = studentName;
        this.studentLastName = studentLastName;
        this.studentType = studentType;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }
}
