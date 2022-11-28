package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    StudentAdapter myStudentAdapter;
    RecyclerView recyclerView;
    Button  goToAdd;
    private StudentViewModel studentViewModel;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         recyclerView = findViewById(R.id.recyclerView);
         goToAdd = findViewById(R.id.goToAdd);

         goToAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i =new Intent(MainActivity.this,AddNewStudentActivity.class);
                 startActivity(i);
             }
         });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        myStudentAdapter=new StudentAdapter(MainActivity.this);
        recyclerView.setAdapter(myStudentAdapter);


        studentViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(StudentViewModel.class);


        studentViewModel.getAllStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                myStudentAdapter.setStudent(students);
            }
        });
    }
}