package source.code;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Lunch implements Parcelable {
	
	private String name;
	private ArrayList<Friend> friends;
	private String comments;
	private String date;
	private String time;
	
	public Lunch(String n)
	{
		name = n;
	}
	
	public void setFriends(ArrayList<Friend> friends) {
	    this.friends = friends;
	}
	
	public ArrayList<Friend> getFriends() {
	    return this.friends;
	}
	
	public void setTime(String time) {
	    this.time = time;
	}
	
	public String getTime() {
	    return this.time;
	}
	
	public void setDate(String date) {
	    this.date = date;
	}
	
	public String getDate() {
	    return this.date;
	}
	
	public String getComments() {
	    return this.comments;
	}
	
	public void setComments(String comments) {
	    this.comments = comments;
	}
	
	public String getTitle()
	{
		return name;
	}
	
	public String toString()
	{
		Object a[] = { name, date,
		        time};

		//TODO: fix formatting so it looks better on invites and attending page
		String s = String.format("%1$-10s %n %3$-8s    %2$s", a);
		return s;
	}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int arg1) {
        out.writeString(name);
        out.writeString(time);
        out.writeString(date);
        out.writeString(comments);
        out.writeArray(friends.toArray());
        
    }
    
    public static final Parcelable.Creator<Lunch> CREATOR = new Parcelable.Creator<Lunch>() {
        public Lunch createFromParcel(Parcel in) {
            return new Lunch(in);
        }

        public Lunch[] newArray(int size) {
            return new Lunch[size];
        }
    };
    
    private Lunch(Parcel in) {
        name = in.readString();
        time = in.readString();
        date = in.readString();
        comments = in.readString();
        friends = in.readArrayList(Friend.class.getClassLoader());
    }

}
