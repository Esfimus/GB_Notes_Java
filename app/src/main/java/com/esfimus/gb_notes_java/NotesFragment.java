package com.esfimus.gb_notes_java;

import android.content.res.Configuration;
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

    private final String CURRENT_NOTE = "note";
    private Note note;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            note = savedInstanceState.getParcelable(CURRENT_NOTE);
            if (isLand() && note != null) {
                showNoteLand(note);
            }
        }

        initNotes(view);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, note);
        super.onSaveInstanceState(outState);
    }

    private void initNotes(View view) {
        // constructing list of notes with only notes' titles
        LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < Note.notesList.size(); i++) {
            TextView noteText = new TextView(getContext());
            noteText.setText(Note.notesList.get(i).getTitle());
            noteText.setTextSize(20);
            layoutView.addView(noteText);
            final int position = i;
            noteText.setOnClickListener(v -> showNote(Note.notesList.get(position)));
        }
    }

    public void showNote(Note note) {
        this.note = note;
        if (isLand()) {
            showNoteLand(note);
        } else {
            showNotePort(note);
        }
    }

    private boolean isLand() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void showNotePort(Note note) {
        DetailsFragment detailsFragment = DetailsFragment.newInstance(note);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_notes, detailsFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    private void showNoteLand(Note note) {
        DetailsFragment detailsFragment = DetailsFragment.newInstance(note);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_details, detailsFragment);
        fragmentTransaction.commit();
    }
}