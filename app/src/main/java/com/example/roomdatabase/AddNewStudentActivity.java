package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewStudentActivity extends AppCompatActivity {
AddNewStudentViewModel addNewStudentViewModel;
    EditText myEditName,myEditLastName,myEditType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
         myEditName= findViewById(R.id.myEditName);
         myEditLastName= findViewById(R.id.myEditLastName);
         myEditType= findViewById(R.id.myEditType);
       Button myButtonAdd= findViewById(R.id.myButtonAdd);

        addNewStudentViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(AddNewStudentViewModel.class);

        myButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNewStudent();
            }
        });
    }

    public void  setNewStudent(){
        String name =myEditName.getText().toString();
        String lastName =myEditLastName.getText().toString();
        String type =myEditType.getText().toString();
        Student student =new Student(name,lastName,type);

        if (name.isEmpty() ||lastName.isEmpty()||type.isEmpty()){
            Toast.makeText(this, "You Must Add Data", Toast.LENGTH_SHORT).show();
        }else {
            addNewStudentViewModel.insert(student);
            Toast.makeText(this, "Success Add Data", Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(AddNewStudentActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}