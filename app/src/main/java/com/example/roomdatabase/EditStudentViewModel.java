package com.example.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class EditStudentViewModel extends AndroidViewModel {



    private  StudentRepository studentRepository;
    public EditStudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository=new StudentRepository(application);

    }

    public  void update(Student student){
        studentRepository.update(student);
    }

}
