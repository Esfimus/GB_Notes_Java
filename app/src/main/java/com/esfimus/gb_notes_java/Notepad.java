package com.esfimus.gb_notes_java;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Notepad {

    ArrayList<Note> notes = new ArrayList<>();

    public void addNote(String title, String message) {
        this.notes.add(new Note(title, message));
    }

    public String readNote(int index) {
        return this.notes.get(index).getTitle() + "\n" +
                this.notes.get(index).getMessage() + "\n" +
                this.notes.get(index).getDate();
    }

    class Note {

        private String title;
        private String message;
        private String date;

        private Note(String title, String message) {
            this.title = title;
            this.message = message;
            this.date = currentDateAndTime();
        }

        private String currentDateAndTime() {
            return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(new Date());
        }

        public String getTitle() {
            return title;
        }

        public String getMessage() {
            return message;
        }

        public String getDate() {
            return date;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
