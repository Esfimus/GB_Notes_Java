package com.esfimus.gb_notes_java;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView() {
        NotesFragment notesFragment = new NotesFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_notes, notesFragment)
                .commit();
    }
}