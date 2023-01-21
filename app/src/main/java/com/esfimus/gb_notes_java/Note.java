package com.esfimus.gb_notes_java;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Note implements Parcelable {

    static ArrayList<Note> notesList = new ArrayList<>();

    static {
        String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Integer quis auctor elit sed vulputate mi sit. Pretium aenean pharetra magna ac placerat vestibulum lectus. Risus pretium quam vulputate dignissim suspendisse in. Amet nisl purus in mollis nunc sed. Nisi scelerisque eu ultrices vitae auctor eu augue ut lectus. Amet commodo nulla facilisi nullam vehicula ipsum a arcu. Tortor id aliquet lectus proin. Metus dictum at tempor commodo ullamcorper a lacus vestibulum. Mollis aliquam ut porttitor leo a diam sollicitudin tempor. Amet consectetur adipiscing elit ut aliquam purus sit. Urna et pharetra pharetra massa. Fermentum dui faucibus in ornare quam viverra. Tincidunt arcu non sodales neque sodales ut etiam sit amet. Nulla aliquet enim tortor at auctor urna nunc id cursus.\n" +
                "\n" +
                "Mauris in aliquam sem fringilla ut morbi tincidunt augue interdum. Curabitur gravida arcu ac tortor dignissim convallis aenean. Nibh tellus molestie nunc non blandit massa. Natoque penatibus et magnis dis parturient montes nascetur. Non consectetur a erat nam at. Et netus et malesuada fames ac turpis egestas sed. At auctor urna nunc id cursus metus. Amet mattis vulputate enim nulla aliquet porttitor lacus. Dignissim enim sit amet venenatis urna. Proin fermentum leo vel orci porta non pulvinar neque. Sed risus pretium quam vulputate dignissim suspendisse in. Venenatis lectus magna fringilla urna porttitor. Nulla aliquet enim tortor at auctor urna nunc id. Risus feugiat in ante metus dictum at tempor commodo.\n" +
                "\n" +
                "Viverra justo nec ultrices dui sapien eget mi proin. Nunc faucibus a pellentesque sit. Semper quis lectus nulla at volutpat diam ut venenatis tellus. Arcu vitae elementum curabitur vitae nunc sed velit dignissim. Vel pretium lectus quam id leo in vitae turpis massa. Fringilla ut morbi tincidunt augue interdum velit euismod in pellentesque. Aliquet porttitor lacus luctus accumsan tortor. Sed enim ut sem viverra aliquet eget sit amet tellus. Tristique nulla aliquet enim tortor at. Mauris pellentesque pulvinar pellentesque habitant morbi. Non odio euismod lacinia at quis risus sed vulputate. Purus sit amet volutpat consequat. Porta lorem mollis aliquam ut porttitor.";
        for (int i = 0; i < 7; i++) {
            notesList.add(new Note(String.format("Title %s", i + 1), String.format("Message %s %s", i + 1, lorem)));
        }
    }

    private String title;
    private String message;
    private String date;

    public Note(String title, String message) {
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

    public void setDate() {
        this.date = currentDateAndTime();
    }

    protected Note(Parcel in) {
        title = in.readString();
        message = in.readString();
        date = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(message);
        dest.writeString(date);
    }
}
