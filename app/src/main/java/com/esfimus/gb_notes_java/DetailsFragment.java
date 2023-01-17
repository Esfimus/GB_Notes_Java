package com.esfimus.gb_notes_java;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    static String TITLE = "title";
    static String MESSAGE = "message";
    static String DATE = "date";

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
            TextView titleDetails = view.findViewById(R.id.text_title);
            TextView messageDetails = view.findViewById(R.id.text_message);
            TextView dateDetails = view.findViewById(R.id.text_date);
            titleDetails.setText(arguments.getString(TITLE));
            messageDetails.setText(arguments.getString(MESSAGE));
            dateDetails.setText(arguments.getString(DATE));
        }
    }

    public static DetailsFragment newInstance(int index, Notepad notepad) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, notepad.notes.get(index).getTitle());
        args.putString(MESSAGE, notepad.notes.get(index).getMessage());
        args.putString(DATE, notepad.notes.get(index).getDate());
        detailsFragment.setArguments(args);
        return detailsFragment;
    }
}