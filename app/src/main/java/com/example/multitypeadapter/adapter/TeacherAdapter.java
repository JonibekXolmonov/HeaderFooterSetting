package com.example.multitypeadapter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multitypeadapter.R;
import com.example.multitypeadapter.model.Teacher;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int TYPE_ITEM_HEADER = 0;
    private final static int TYPE_PROFESSOR_YES = 1;
    private final static int TYPE_PROFESSOR_NO = 2;
    private final static int TYPE_ITEM_FOOTER = 3;
    ArrayList<Teacher> teachers;

    public TeacherAdapter(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public int getItemViewType(int position) {
        Teacher teacher = teachers.get(position);

        if (isHeader(position)) return TYPE_ITEM_HEADER;
        if (isFooter(position)) return TYPE_ITEM_FOOTER;

        if (teacher.getProfessor()) {
            return TYPE_PROFESSOR_YES;
        }
        return TYPE_PROFESSOR_NO;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    public boolean isFooter(int position) {
        return position == teachers.size() - 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_PROFESSOR_YES) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_professor, parent, false);
            return new TeacherProfessorViewHolder(view);
        } else if (viewType == TYPE_PROFESSOR_NO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_non_professor, parent, false);
            return new TeacherNonProfessorViewHolder(view);
        } else if (viewType == TYPE_ITEM_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header, parent, false);
            return new HeaderViewHolder(view);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_footer, parent, false);
        return new FooterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Teacher teacher = teachers.get(position);

        if (isHeader(position) || isFooter(position)) return;

        if (holder instanceof TeacherProfessorViewHolder) {
            ((TeacherProfessorViewHolder) holder).fullName.setText(teacher.getName());
            ((TeacherProfessorViewHolder) holder).subject.setText(teacher.getSubject());
            ((TeacherProfessorViewHolder) holder).profile.setImageResource(teacher.getProfile());
        }

        if (holder instanceof TeacherNonProfessorViewHolder) {
            ((TeacherNonProfessorViewHolder) holder).fullName.setText(teacher.getName());
            ((TeacherNonProfessorViewHolder) holder).subject.setText(teacher.getSubject());
            ((TeacherNonProfessorViewHolder) holder).profile.setImageResource(teacher.getProfile());
        }
    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }

    static class TeacherProfessorViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView fullName, subject;
        public ImageView profile;

        public TeacherProfessorViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            fullName = view.findViewById(R.id.tv_teacher_full_name);
            subject = view.findViewById(R.id.tv_subject);
            profile = view.findViewById(R.id.imv_teacher_profile);
        }
    }

    static class TeacherNonProfessorViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView fullName, subject;
        public ImageView profile;

        public TeacherNonProfessorViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            fullName = view.findViewById(R.id.tv_teacher_full_name_non);
            subject = view.findViewById(R.id.tv_subject_non);
            profile = view.findViewById(R.id.imv_teacher_profile_non);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
