package com.example.movieandsongcatalogue.data;

import android.os.Parcel;
import android.os.Parcelable;


public class Detail implements Parcelable {
    //to string method
    @Override
    public String toString() {
        return "Details{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private String name;
    private String description;

    //getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    //setters
    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

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