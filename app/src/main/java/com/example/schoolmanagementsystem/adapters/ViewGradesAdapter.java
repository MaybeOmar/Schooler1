package com.example.schoolmanagementsystem.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolmanagementsystem.Models.StudentGrade;
import com.example.schoolmanagementsystem.Models.Subject;
import com.example.schoolmanagementsystem.R;

import java.util.List;

public class ViewGradesAdapter extends RecyclerView.Adapter<ViewGradesAdapter.ViewHolder> {

    private List<StudentGrade> studentGrades;
    private Context context;

    public ViewGradesAdapter(List<StudentGrade> studentGrades, Context context) {
        this.studentGrades = studentGrades;
        this.context = context;
    }

    public ViewGradesAdapter(){}

    @NonNull
    @Override
    public ViewGradesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.single_subject_layout,parent,false);
        return new ViewGradesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewGradesAdapter.ViewHolder holder, int position) {
        StudentGrade studentGrade = studentGrades.get(position);
        holder.studentID.setText(studentGrade.getStudentId());
        holder.grade.setText(studentGrade.getGrade());
    }

    @Override
    public int getItemCount() {
        return studentGrades.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView studentID, grade;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentID =itemView.findViewById(R.id.studentNameTV);
            grade =itemView.findViewById(R.id.studentIDv);
        }
    }
}
