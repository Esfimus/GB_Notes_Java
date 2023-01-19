package com.esfimus.gb_notes_java;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

            view.findViewById(R.id.floating_action_button).setOnClickListener(v -> toastCheck());

            titleDetails.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    note.setTitle(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) { }
            });

            messageDetails.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    note.setMessage(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) { }
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

    private void toastCheck() {
        Toast.makeText(getContext(), "button works", Toast.LENGTH_LONG).show();
    }

}