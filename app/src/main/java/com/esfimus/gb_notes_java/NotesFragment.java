package com.esfimus.gb_notes_java;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initNotes(view);
    }

    public void initNotes(View view) {
        // creating some sample notes
        Notepad notepad = new Notepad();
        notepad.addNote("First note First note First note First note First note First note", "This is the first note This is the first note This is the first note This is the first note This is the first note This is the first note This is the first note This is the first note This is the first note This is the first note This is the first note ", "23.01.14");
        notepad.addNote("Second note", "This is the second note", "23.01.15");
        notepad.addNote("Third note", "This is the third note", "23.01.16");

        // constructing list of notes with only notes' titles
        LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < notepad.notes.size(); i++) {
            TextView noteText = new TextView(getContext());
            noteText.setText(notepad.notes.get(i).getTitle());
            noteText.setTextSize(20);
            layoutView.addView(noteText);
            final int position = i;
            noteText.setOnClickListener(v -> showNote(position, notepad));
        }
    }

    public void showNote(int index, Notepad notepad) {
        DetailsFragment detailsFragment = DetailsFragment.newInstance(index, notepad);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, detailsFragment);
        fragmentTransaction.addToBackStack("noname");
        fragmentTransaction.commit();
    }
}