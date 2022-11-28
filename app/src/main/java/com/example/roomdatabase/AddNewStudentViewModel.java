package com.example.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AddNewStudentViewModel extends AndroidViewModel {


    private  StudentRepository studentRepository;
    public AddNewStudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository=new StudentRepository(application);

    }

    public  void insert(Student student){
        studentRepository.insert(student);
    }

}
