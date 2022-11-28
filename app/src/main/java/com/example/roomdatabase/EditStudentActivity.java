package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudentActivity extends AppCompatActivity {
    EditText myEditName,myEditLastName,myEditType;
EditStudentViewModel editStudentViewModel;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sudint);

        editStudentViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(EditStudentViewModel.class);

        myEditName= findViewById(R.id.myEditName);
        myEditLastName= findViewById(R.id.myEditLastName);
        myEditType= findViewById(R.id.myEditType);
        Button myButtonEdit= findViewById(R.id.myButtonEdit);

        Bundle bundle =getIntent().getExtras();

       String name= bundle.getString("name");
       String lastName= bundle.getString("lastName");
       String type= bundle.getString("type");
         id= bundle.getInt("id");

       ///
       myEditName.setText(name);
       myEditLastName.setText(lastName);
       myEditType.setText(type);

        myButtonEdit.setOnClickListener(new View.OnClickListener() {
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
            student.setId(id);

            editStudentViewModel.update(student);
            Toast.makeText(this, "Success Edit Data", Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(EditStudentActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
