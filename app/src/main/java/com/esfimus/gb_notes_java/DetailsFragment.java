package com.esfimus.gb_notes_java;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    static final String CURRENT_NOTE = "note";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Note note = arguments.getParcelable(CURRENT_NOTE);
            TextView titleDetails = view.findViewById(R.id.text_title);
            TextView messageDetails = view.findViewById(R.id.text_message);
            TextView dateDetails = view.findViewById(R.id.text_date);
            titleDetails.setText(note.getTitle());
            messageDetails.setText(note.getMessage());
            dateDetails.setText(note.getDate());
            // starting new note fragment by FAB or long click
            view.findViewById(R.id.floating_action_button).setOnClickListener(v -> initEditNoteFragment(note));
            view.findViewById(R.id.fragment_details_container).setOnLongClickListener(v -> {
                initEditNoteFragment(note);
                return true;
            });
        }
    }

    public static DetailsFragment newInstance(Note note) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_NOTE, note);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    private boolean isLand() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initEditNoteFragment(Note note) {
        if (isLand()) {
            editNoteLand(note);
        } else {
            editNotePort(note);
        }
    }

    private void editNotePort(Note note) {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_notes, EditNoteFragment.newInstance(note))
                .addToBackStack("")
                .commit();
    }

    private void editNoteLand(Note note) {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_details, EditNoteFragment.newInstance(note))
                .addToBackStack("")
                .commit();
    }

}