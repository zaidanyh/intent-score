package id.putraprima.skorbola.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Skor implements Parcelable {
    private String hometext, awaytext;
    private Uri homeUri;

    public Uri getHomeUri() {
        return homeUri;
    }

    public void setHomeUri(Uri homeUri) {
        this.homeUri = homeUri;
    }

    public Uri getAwayUri() {
        return awayUri;
    }

    public void setAwayUri(Uri awayUri) {
        this.awayUri = awayUri;
    }

    public static Creator<Skor> getCREATOR() {
        return CREATOR;
    }

    private Uri awayUri;

    public Skor(String hometext, String awaytext, Uri homeUri, Uri awayUri) {
        this.hometext = hometext;
        this.awaytext = awaytext;
        this.homeUri = homeUri;
        this.awayUri = awayUri;
    }

    protected Skor(Parcel in) {
        this.hometext = in.readString();
        this.awaytext = in.readString();
        this.homeUri = in.readParcelable(Uri.class.getClassLoader());
        this.awayUri = in.readParcelable(Uri.class.getClassLoader());
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
        dest.writeString(this.hometext);
        dest.writeString(this.awaytext);
        dest.writeParcelable(this.homeUri, flags);
        dest.writeParcelable(this.awayUri, flags);

    }
}
