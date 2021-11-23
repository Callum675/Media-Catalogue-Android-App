package com.example.movieandsongcatalogue.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Detail")
public class Detail implements Parcelable {
    //to string method
    @Override
    public String toString() {
        return "Details{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private String name; //name of media
    private String description; //description of media
    private String link; //link to more information
    private String note; //String for holding user notes

    //database id
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    //getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getLink() {
        return link;
    }
    public String getNote() { return note; }
    public int getUid() { return uid; }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }

    //parcel functions
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(getName());
        out.writeString(getDescription());
    }

    public static final Parcelable.Creator<Detail> CREATOR
            = new Parcelable.Creator<Detail>() {
        public Detail createFromParcel(Parcel in) {
            Detail details = new Detail();
            details.setName(in.readString());
            details.setDescription(in.readString());
            return details;
        }

        public Detail[] newArray(int size) {
            return new Detail[size];
        }
    };
}