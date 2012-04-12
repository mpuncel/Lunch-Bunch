package source.code;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable{
    
    private String name;
    private int mData;
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }
    
    public Friend(String n) {
        this.name = n;
    }
    
    public String toString() {
        
        return this.name;
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
        mData = in.readInt();
    }


}
