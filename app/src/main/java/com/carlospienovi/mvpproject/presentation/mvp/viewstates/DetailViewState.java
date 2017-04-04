package com.carlospienovi.mvpproject.presentation.mvp.viewstates;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailViewState implements Parcelable {

    private String title;
    private String body;

    public DetailViewState(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


    protected DetailViewState(Parcel in) {
        title = in.readString();
        body = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(body);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DetailViewState> CREATOR = new Parcelable.Creator<DetailViewState>() {
        @Override
        public DetailViewState createFromParcel(Parcel in) {
            return new DetailViewState(in);
        }

        @Override
        public DetailViewState[] newArray(int size) {
            return new DetailViewState[size];
        }
    };

}
