package com.example.roomdatabase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    List<Student> students = new ArrayList<>();
    Context context;

    public StudentAdapter(Context context1) {

        this.context = context1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = students.get(position);
        holder.textView.setText(student.getStudentName());
        holder.textView1.setText(student.getStudentLastName());
        holder.textView3.setText(student.getStudentType() + "");
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setStudent(List<Student> student) {
        students = student;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView, textView1, textView3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.id_myText1);
            textView1 = itemView.findViewById(R.id.id_myText2);
            textView3 = itemView.findViewById(R.id.id_myText3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    Intent intent = new Intent(context, EditStudentActivity.class);
                    intent.putExtra("name", students.get(index).getStudentName());
                    intent.putExtra("lastName", students.get(index).getStudentLastName());
                    intent.putExtra("type", students.get(index).getStudentType());
                    intent.putExtra("id", students.get(index).getId());
                    context.startActivity(intent);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int index = getAdapterPosition();
                    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                    int width = metrics.widthPixels;
                    int height = metrics.heightPixels;

                    MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("Delete", "Are You Shore To Delete Student  " + students.get(index).getStudentName(), context, index);
                    myDialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), null);

                    return false;
                }
            });


        }
    }
}