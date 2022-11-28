package com.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {


    private StudentDao studentDao;
    private LiveData<List<Student>> getStudent;

    public StudentRepository(Application application) {
        StudentRoomDataBase roomDataBase = StudentRoomDataBase.getInstance(application);
        studentDao = roomDataBase.studentDao();
        getStudent = studentDao.getAllStudent();

    }


    void insert(Student student) {
        new insertAsyncTask(studentDao).execute(student);
    }

    void delete(Student student) {
        new deleteAsyncTask(studentDao).execute(student);


    }

    void update(Student student) {
        new updateAsyncTask(studentDao).execute(student);


    }

    LiveData<List<Student>> getAllStudent() {

        return getStudent;
    }


    private static class insertAsyncTask extends AsyncTask<Student, Void, Void> {
        StudentDao mStudentDao;

        public insertAsyncTask(StudentDao studentDao) {
            mStudentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            mStudentDao.insert(students[0]);
            return null;
        }
    }


    private static class updateAsyncTask extends AsyncTask<Student, Void, Void> {
        StudentDao mStudentDao;

        public updateAsyncTask(StudentDao studentDao) {
            mStudentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            mStudentDao.update(students[0]);
            return null;
        }
    }


    private static class deleteAsyncTask extends AsyncTask<Student, Void, Void> {
        StudentDao mStudentDao;

        public deleteAsyncTask(StudentDao studentDao) {
            mStudentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            mStudentDao.delete(students[0]);
            return null;
        }
    }
}
