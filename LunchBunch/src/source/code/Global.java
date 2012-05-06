package source.code;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Application;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Context;

public class Global extends Application 
{
	private ArrayList<Lunch> lunchInvites;
	private ArrayList<Lunch> lunchesAttending;
	private Lunch currentCreatingLunch;
	private Lunch currentClickedLunch;
	private ArrayList<Friend> lunchFriends;
	private FriendListAdapter<Friend> friendListAdapter;
	private NotificationManager notificationManager;
	private final static int LUNCH_ID = 0;
	
	
    public void makeLunches()
    {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        if (lunchInvites == null) {
            Calendar systemTime = Calendar.getInstance();
            Calendar firstLunchTime = (Calendar)systemTime.clone();
            firstLunchTime.add(Calendar.DAY_OF_YEAR, 1);  
            firstLunchTime.add(Calendar.MINUTE, 35);  
        	ArrayList<Friend> attending = new ArrayList<Friend>();
        	attending.add(new Friend("Anjali Muralidhar"));
        	attending.add(new Friend("Mike Puncel"));
        	attending.add(new Friend("Pallavi Powale"));
            lunchInvites = new ArrayList<Lunch>();
            lunchesAttending = new ArrayList<Lunch>();
            Lunch tbell = new Lunch("Taco Bell");
            tbell.setLunchTime(firstLunchTime);
            tbell.setFriends(attending);
            Lunch cosi = new Lunch("Cosi");
            Calendar secondLunchTime = (Calendar)firstLunchTime.clone();
            secondLunchTime.add(Calendar.DAY_OF_YEAR, 1);
            secondLunchTime.add(Calendar.MINUTE, 35);
            cosi.setLunchTime(secondLunchTime);
            cosi.setFriends(attending);
            Lunch masa = new Lunch("Masa");
            Calendar thirdLunchTime = (Calendar)secondLunchTime.clone();
            thirdLunchTime.add(Calendar.MINUTE, 35);
            thirdLunchTime.add(Calendar.DAY_OF_YEAR, 1);
            masa.setLunchTime(thirdLunchTime);
            masa.setFriends(attending);
            lunchInvites.add(tbell);
            lunchInvites.add(cosi);
            lunchInvites.add(masa);
            
            Lunch dhaba = new Lunch("Desi Dhaba");
            Calendar attendTime = (Calendar)systemTime.clone();
            attendTime.add(Calendar.MINUTE, 35);
            dhaba.setLunchTime(attendTime);
            dhaba.setFriends(attending);
            dhaba.setReminderTime(30);
            LunchNotificationBuilder dhabaNotificationBuilder = new LunchNotificationBuilder(dhaba, getApplicationContext());
            Notification dhabaNotification = dhabaNotificationBuilder.getNotification();
            notificationManager.notify(LUNCH_ID, dhabaNotification);
            
            
            Lunch maggianos = new Lunch("Maggiano's");
            Calendar attendTime2 = (Calendar)systemTime.clone();
            attendTime2.add(Calendar.MINUTE, 60);
            maggianos.setLunchTime(attendTime2);
            maggianos.setFriends(attending);
            maggianos.setReminderTime(30);
            lunchesAttending.add(dhaba);
            lunchesAttending.add(maggianos);
        }
    }
    
	public void createLunchDone() {
	    lunchesAttending.add(currentCreatingLunch);
	    //currentCreatingLunch = null;
	}
	
	public Lunch getCurrentCreatingLunch() {
	    return currentCreatingLunch;
	}
	public void setCurrentCreatingLunch(Lunch l) { 
	    currentCreatingLunch = l;
	}
	
	public Lunch getCreatingLunch() {
	    return this.currentCreatingLunch;
	}
	
	public void setCurrentClickedLunch(Lunch l) { 
	    currentClickedLunch = l;
	}
	
	public Lunch getCurrentClickedLunch() {
	    return this.currentClickedLunch;
	}
	
	
	public ArrayList<Friend> getLunchFriends() {
	    return lunchFriends;
	}
	
	public void addLunchInvite(Lunch lunch){
		lunchInvites.add(lunch);
	}
	
	public void addLunchAttending(Lunch lunch){
		lunchesAttending.add(lunch);
	}
	
	public void removeLunchInvite(String lunchTitle){
		for (int i = 0; i < lunchInvites.size(); i++)
		{
			if (lunchTitle.startsWith(lunchInvites.get(i).getTitle()))
			{
				lunchInvites.remove(i);
				break;
			}
		}
	}
	
	public void removeLunchesAttending(String lunchTitle){
		for (int i = 0; i < lunchesAttending.size(); i++)
		{
			if (lunchTitle.startsWith(lunchesAttending.get(i).getTitle()))
			{
				lunchesAttending.remove(i);
				break;
			}
		}
	}
	
	public void setLunchInvites(ArrayList<Lunch> lunches)
	{
		lunchInvites = lunches;
	}
	
	public void setLunchesAttending(ArrayList<Lunch> lunches)
	{
		lunchesAttending = lunches;
	}
	
	public ArrayList<Lunch> getLunchInvites()
	{
		return lunchInvites;
	}
	
	public Lunch getLunchInvite(int position) {
	    return lunchInvites.get(position);
	}
	
	public void removeLunchInvite(int position) {
	    lunchInvites.remove(position);
	}
	
	public ArrayList<Lunch> getLunchesAttending() {
		return lunchesAttending;
	}
	
	public Lunch getLunchAttending(int position) {
	    return lunchesAttending.get(position);
	}
	
	public void removeLunchAttending(int position) {
	    lunchesAttending.remove(position);
	}
	
	public void setFriendListAdapter(FriendListAdapter<Friend> friendListAdapter) {
	    this.friendListAdapter = friendListAdapter;
	}
	
	public FriendListAdapter<Friend> getFriendListAdapter() {
	    return this.friendListAdapter;
	}
	
}
