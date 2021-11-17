package com.example.movieandsongcatalogue.data;


import android.os.Parcel;
import android.os.Parcelable;

public class Search implements Parcelable {

    private String search;
    private String searchDescription;
    private String searchLink;

    //getter
    public  String getSearch() { return search; }
    public  String getSearchDescription() {
        return searchDescription;
    }
    public  String getLink() { return searchLink; }

    //setter
    public void setSearch(String search) { this.search = search; }
    public void setSearchDescription(String searchDescription) { this.searchDescription = searchDescription; }
    public void setSearchLink(String searchLink) { this.searchLink = searchLink; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(getSearch());
        out.writeString(getSearchDescription());
    }

    public static final Parcelable.Creator<Search> CREATOR
            = new Parcelable.Creator<Search>() {
        public Search createFromParcel(Parcel in) {
            Search search = new Search();
            search.setSearch(in.readString());
            search.setSearchDescription(in.readString());
            return search;
        }

        public Search[] newArray(int size) {
            return new Search[size];
        }
    };

}
