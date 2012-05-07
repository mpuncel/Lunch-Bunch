package source.code;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.Time;

public class Lunch implements Parcelable, Comparable {
	
	private String name;
	private ArrayList<Friend> friends;
	private HashSet<Friend> acceptedfriends = new HashSet<Friend>();
	private String comments;
	private String date;
	private String time;
	private Calendar lunchTime;
	private boolean isConfirmed;
	private boolean reminderRequested;
	private boolean isDeclined;
	private int reminderTime;
	
	public Lunch(String n)
	{
		name = n;
	}
	
	public void setReminderTime(int reminderTime) {
	    this.reminderTime = reminderTime;
	}
	
	public int getReminderTime() {
	    return this.reminderTime;
	}
	
	public void setFriends(ArrayList<Friend> friends) {
	    this.friends = friends;
	}
	

	public void addAcceptedFriend(Friend friend) {
	    this.acceptedfriends.add(friend);
	}
	
	public void removeAcceptedFriend(Friend friend) {
	    this.acceptedfriends.remove(friend);
	}
	

	public void setTitle(String location)
	{
		name = location;
	}
	public ArrayList<Friend> getFriends() {
	    return this.friends;
	}
	
	public HashSet<Friend> getAcceptedFriends() {
	    return this.acceptedfriends;
	}
	
	public String getTime() {
	    return this.time;
	}
	
	
	public String getDate() {
	    return this.date;
	}
	
	public void setLunchTime(Calendar lunchTime) {
	    this.lunchTime = lunchTime;
	    String fullDate = lunchTime.getTime().toString();
	    String[] splitDate = fullDate.split(" ");
	    this.date = splitDate[0] + ", " + splitDate[1] + " " + splitDate[2];
	    String[] timeValues = splitDate[3].split(":");
	    int hour = Integer.valueOf(timeValues[0]);
	    String modifier = "am";
	    if (hour > 12) {
	        hour -= 12;
	        modifier = "pm";
	    }
	    this.time = String.valueOf(hour) + ":" + timeValues[1] + " " + modifier;
	}
	
	public Calendar getLunchTime() {
	    return this.lunchTime;
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

	

	
	public int compareTo(Object otherlunch) {
		Date mydate, date2;
		long mytime, time2;
	    int year, month, day, hour, minute;
	    
	   
	    
    	//String[] date =this.getDate().split("/");
    	//System.out.println(date);
    	//System.out.println(this.getDate());
    	//year = Integer.parseInt(date[2]);
    	
    	//month = Integer.parseInt(date[0]);
    	//System.out.println("here9");
    	//day = Integer.parseInt(date[1]);
    	
    	//String[] time =this.getTime().split(":");
    	//String[] timeb =time[1].split(" ");
    	//hour =Integer.parseInt(time[0]);
    	//minute =Integer.parseInt(timeb[0]);
    	//if(timeb[1].equals("pm")){
    	//	hour+=12;
    	//}
    	
    	//mydate = new Date(year, month, day);
    	//mytime = mydate.getTime()+hour*3600000+minute*60000; //in milliseconds
	    mytime = this.lunchTime.getTimeInMillis();
    	
    	//date =((Lunch)otherlunch).getDate().split("/");
    	//year = Integer.parseInt(date[2]);
    	//month = Integer.parseInt(date[0]);
    	//day = Integer.parseInt(date[1]);
    	
    	//time =((Lunch)otherlunch).getTime().split(":");
    	//timeb =time[1].split(" ");
    	//hour =Integer.parseInt(time[0]);
    	//minute =Integer.parseInt(timeb[0]);
    	//if(timeb[1].equals("pm")){
    	////	hour+=12;
    	//}
    	
    	//date2 = new Date(year, month, day);
    	//time2 = date2.getTime()+hour*3600000+minute*60000; //in milliseconds
		time2 = ((Lunch)otherlunch).getLunchTime().getTimeInMillis();

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
