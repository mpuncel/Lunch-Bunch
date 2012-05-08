package source.code;

import java.io.IOException;
import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable, Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -528407186425280394L;
    private String name;
    private int namelength;
    private boolean accepted;
    
    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        out.writeInt(this.namelength);
        for (int i = 0; i < this.namelength; i++) {
            out.writeChar(this.name.charAt(i));
        }
        out.writeBoolean(accepted);
    }
    
    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        namelength = in.readInt();
        name = "";
        for (int i = 0; i < this.namelength; i++) {
            name = name.concat(String.valueOf(in.readChar()));
        }
        accepted = in.readBoolean();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
    }
    
    public Friend(String n) {
        this.name = n;
        this.namelength = this.name.length();
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
