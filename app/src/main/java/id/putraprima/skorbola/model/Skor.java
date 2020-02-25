package id.putraprima.skorbola.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Skor implements Parcelable {
    private String hometext;
    private String awaytext;

    public Skor(String hometext, String awaytext) {
        this.hometext = hometext;
        this.awaytext = awaytext;
    }

    protected Skor(Parcel in) {
        hometext = in.readString();
        awaytext = in.readString();
    }

    public static final Creator<Skor> CREATOR = new Creator<Skor>() {
        @Override
        public Skor createFromParcel(Parcel in) {
            return new Skor(in);
        }

        @Override
        public Skor[] newArray(int size) {
            return new Skor[size];
        }
    };

    public String getHometext() {
        return hometext;
    }

    public void setHometext(String hometext) {
        this.hometext = hometext;
    }

    public String getAwaytext() {
        return awaytext;
    }

    public void setAwaytext(String awaytext) {
        this.awaytext = awaytext;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hometext);
        dest.writeString(awaytext);
    }
}
