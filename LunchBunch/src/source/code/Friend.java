package source.code;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable{
    
    private String name;
    private boolean accepted;
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
    }
    
    public Friend(String n) {
        this.name = n;
        this.accepted = false;
    }
    
    public String toString() {
        
        return this.name;
    }
    
    public boolean getAccepted() {
        return this.accepted;
    }
    
    public void setAccepted() {
        this.accepted = true;
    }
    
    public static final Parcelable.Creator<Friend> CREATOR = new Parcelable.Creator<Friend>() {
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };
    
    private Friend(Parcel in) {
        name = in.readString();
    }


}
