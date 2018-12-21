package com.dilerdesenvolv.cinqagendateste.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Gallery implements Parcelable {

    private int userId;
    private int id;
    private String title;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Gallery() {}

    // PARCELABLE
    public Gallery(Parcel parcel) {
        setUserId(parcel.readInt());
        setId(parcel.readInt());
        setTitle(parcel.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getUserId());
        dest.writeInt(getId());
        dest.writeString(getTitle());
    }

    public static final Parcelable.Creator<Gallery> CREATOR = new Parcelable.Creator<Gallery>() {
        @Override
        public Gallery createFromParcel(Parcel source) {
            return new Gallery(source);
        }
        @Override
        public Gallery[] newArray(int size) {
            return new Gallery[size];
        }
    };

}
