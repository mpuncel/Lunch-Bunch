package source.code;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

import android.os.Parcel;
import android.os.Parcelable;

public class Lunch implements Parcelable, Comparable {
	
	private String name;
	private ArrayList<Friend> friends;
	private HashMap<String, Friend> acceptedfriends = new HashMap<String, Friend>();
	private String comments;
	private String date;
	private String time;
	private Calendar lunchTime;
	private boolean isConfirmed;
	private boolean reminderRequested;
	private boolean isDeclined;
	private boolean isMine;
	private boolean confirmationRequested;
	private Calendar reminderTime;
	private int reminderOffset;

	public Lunch(String n)
	{
		name = n;
		setMine(false);
	}
	
	public void setReminderTime(int reminderTimeOffset) {
	    Calendar reminderTime = (Calendar)this.lunchTime.clone();
	    reminderTime.add(Calendar.MINUTE, -1 * reminderTimeOffset);
	    this.reminderTime = reminderTime;
	    this.reminderOffset = reminderTimeOffset;
	}
	
	public int getReminderOffset() {
	    return this.reminderOffset;
	}
	
	public Calendar getReminderTime() {
	    return this.reminderTime;
	}
	
	public void setFriends(ArrayList<Friend> friends) {
	    this.friends = friends;
	}
	

	public void addAcceptedFriend(Friend friend) {
	    this.acceptedfriends.put(friend.toString(), friend);
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
	
	public HashMap<String, Friend> getAcceptedFriends() {
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
	    this.date = splitDate[0] + ", " + splitDate[1] + " " + Integer.parseInt(splitDate[2]);
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
	
	/*public String toString()
	{
		Object a[] = { name, date,
		        time};

		//TODO: fix formatting so it looks better on invites and attending page
		String s = String.format("%1$-10s %n %3$-8s    %2$12s", a);
		return s;
	}*/

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int arg1) {
        out.writeString(name);
        out.writeString(time);
        out.writeString(date);
        out.writeString(comments);
        out.writeTypedList(friends);
        out.writeMap(acceptedfriends);
        out.writeValue(lunchTime);
        boolean[] boolarray = {isConfirmed, reminderRequested, isDeclined, isMine, confirmationRequested};
        out.writeBooleanArray(boolarray);
        out.writeValue(reminderTime);
        out.writeInt(reminderOffset);
        
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
        friends = new ArrayList<Friend>();
        acceptedfriends = new HashMap<String, Friend>();
        name = in.readString();
        time = in.readString();
        date = in.readString();
        comments = in.readString();
        in.readTypedList(friends, Friend.CREATOR);
        in.readMap(acceptedfriends, Friend.class.getClassLoader());
        lunchTime = (Calendar)in.readValue(Calendar.class.getClassLoader());
        boolean[] boolarray = new boolean[5];
        in.readBooleanArray(boolarray);
        isConfirmed = boolarray[0];
        reminderRequested = boolarray[1];
        isDeclined = boolarray[2];
        isMine = boolarray[3];
        confirmationRequested = boolarray[4];
        reminderTime = (Calendar)in.readValue(Calendar.class.getClassLoader());
        reminderOffset = in.readInt();
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

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean isConfirmationRequested() {
		return confirmationRequested;
	}

	public void setConfirmationRequested(boolean confirmationRequested) {
		this.confirmationRequested = confirmationRequested;
	}

}
