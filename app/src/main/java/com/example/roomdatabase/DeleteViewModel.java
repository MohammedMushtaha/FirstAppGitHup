package com.example.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class DeleteViewModel extends AndroidViewModel {
    private  StudentRepository studentRepository;
    public DeleteViewModel(@NonNull Application application) {
        super(application);
        studentRepository=new StudentRepository(application);

    }

    public  void delete(Student student){
        studentRepository.delete(student);
    }

}
