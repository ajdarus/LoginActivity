package me.arsnotfound.myfirstproject.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Credentials implements Parcelable {
    private final String username;
    private final String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected Credentials(Parcel in) {
        username = in.readString();
        password = in.readString();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(password);
    }

    public static final Creator<Credentials> CREATOR = new Creator<>() {
        @Override
        public Credentials createFromParcel(Parcel in) {
            return new Credentials(in);
        }

        @Override
        public Credentials[] newArray(int size) {
            return new Credentials[size];
        }
    };
}
