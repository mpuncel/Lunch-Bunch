package source.code;

import java.sql.Date;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.Time;

public class Lunch implements Parcelable, Comparable {
	
	private String name;
	private ArrayList<Friend> friends;
	private String comments;
	private String date;
	private String time;
	private boolean isConfirmed;
	private boolean reminderRequested;
	private boolean isDeclined;
	
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
		String s = String.format("%1$-10s %n %3$-8s    %2$12s", a);
		return s;
	}

    public int describeContents() {
        return 0;
    }

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

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public boolean isReminderRequested() {
		return reminderRequested;
	}

	public void setReminderRequested(boolean reminderRequested) {
		this.reminderRequested = reminderRequested;
	}

	public boolean isDeclined() {
		return isDeclined;
	}

	public void setDeclined(boolean isDeclined) {
		this.isDeclined = isDeclined;
	}

	

	@Override
	public int compareTo(Object otherlunch) {
		Date mydate, date2;
		long mytime, time2;
	    int year, month, day, hour, minute;
	    

	    
    	String[] date =this.getDate().split("/");
    	year = Integer.parseInt(date[2]);
    	month = Integer.parseInt(date[0]);
    	day = Integer.parseInt(date[1]);
    	
    	String[] time =this.getTime().split(":");
    	String[] timeb =time[1].split(" ");
    	hour =Integer.parseInt(time[0]);
    	minute =Integer.parseInt(timeb[0]);
    	if(timeb[1].equals("pm")){
    		hour+=12;
    	}
    	
    	mydate = new Date(year, month, day);
    	mytime = mydate.getTime()+hour*3600000+minute*60000; //in milliseconds
    	
    	date =((Lunch)otherlunch).getDate().split("/");
    	year = Integer.parseInt(date[2]);
    	month = Integer.parseInt(date[0]);
    	day = Integer.parseInt(date[1]);
    	
    	time =((Lunch)otherlunch).getTime().split(":");
    	timeb =time[1].split(" ");
    	hour =Integer.parseInt(time[0]);
    	minute =Integer.parseInt(timeb[0]);
    	if(timeb[1].equals("pm")){
    		hour+=12;
    	}
    	
    	date2 = new Date(year, month, day);
    	time2 = date2.getTime()+hour*3600000+minute*60000; //in milliseconds

		if (mytime<time2){
			return -1;
		}
		else if (mytime>time2){
			return 1;
		}
		else if (mytime==time2){
			return 0;
		}
		return 0;
	}

}
