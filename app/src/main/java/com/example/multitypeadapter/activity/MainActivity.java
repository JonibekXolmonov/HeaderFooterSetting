package com.example.multitypeadapter.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.multitypeadapter.R;
import com.example.multitypeadapter.adapter.TeacherAdapter;
import com.example.multitypeadapter.model.Teacher;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Teacher> teachers = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillTeachersList();
        initViews();
    }

    void initViews() {
        recyclerView = findViewById(R.id.rv_teachers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        refreshAdapter();
    }

    void refreshAdapter() {
        TeacherAdapter teacherAdapter = new TeacherAdapter(teachers);
        recyclerView.setAdapter(teacherAdapter);
    }

    void fillTeachersList() {

        teachers.add(new Teacher());

        for (int i = 1; i < 100; i++) {
            if (i % 5 == 0)
                teachers.add(new Teacher(setDefaultName(), setDefaultSubject(), false, R.drawable.image_teacher));
            else
                teachers.add(new Teacher(setDefaultName(), setDefaultSubject(), true, R.drawable.image_teacher));
        }

        teachers.add(new Teacher());
    }

    private String setDefaultSubject() {
        String[] names = {"Math", "English", "Physics", "Biology", "Discrete math", "Geology", "Philosophy"};

        return names[new Random().nextInt(names.length)];
    }

    private String setDefaultName() {
        String[] subjects = {"Jamshid Sobirov", "Jahongir Mannonov", "Jonibek Xolmonov", "Asliddin Kenjayev", "Sardor Ergashev", "Saidahmad Ataullayev", "Javohir Karimov"};

        return subjects[new Random().nextInt(subjects.length)];
    }
}