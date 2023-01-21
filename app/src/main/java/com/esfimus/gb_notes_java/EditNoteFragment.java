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

public class EditNoteFragment extends Fragment {

    static final String CURRENT_NOTE = "note";
    static final String[] DATA_TYPE = {"Title", "Message"};

    public EditNoteFragment() {}

    public static EditNoteFragment newInstance(Note note) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (getArguments() != null) {
            // recreating all string values of current note
            Note note = arguments.getParcelable(CURRENT_NOTE);
            TextView titleEdit = view.findViewById(R.id.edit_text_title);
            TextView messageEdit = view.findViewById(R.id.edit_text_message);
            TextView dataEdit = view.findViewById(R.id.edit_text_date);
            titleEdit.setText(note.getTitle());
            messageEdit.setText(note.getMessage());
            dataEdit.setText(note.getDate());
            // changing text fields
            textChange(note, titleEdit, DATA_TYPE[0]);
            textChange(note, messageEdit, DATA_TYPE[1]);
        }
    }

    private void textChange(Note note, TextView text, String type) {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            // changing chosen text field and resetting the time
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switch (type) {
                    case "Title" : {
                        note.setTitle(s.toString());
                        break;
                    }
                    case "Message" : {
                        note.setMessage(s.toString());
                        break;
                    }
                }
                note.setDate();
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
}