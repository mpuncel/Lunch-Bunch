package source.code;

import java.util.ArrayList;

import android.app.Application;

public class Global extends Application 
{
	private ArrayList<Lunch> lunchInvites;
	private ArrayList<Lunch> lunchesAttending;
	private Lunch currentCreatingLunch;
	private ArrayList<Friend> lunchFriends;
	private Lunch clickedLunch = null;
	
	
    public void makeLunches()
    {
        if (lunchInvites == null) {
            lunchInvites = new ArrayList<Lunch>();
            lunchesAttending = new ArrayList<Lunch>();
            Lunch tbell = new Lunch("Taco Bell");
            tbell.setDate("5/7/2012");
            tbell.setTime("12:00 pm");
            Lunch cosi = new Lunch("Cosi");
            cosi.setDate("5/8/2012");
            cosi.setTime("12:00 pm");
            Lunch masa = new Lunch("Masa");
            masa.setDate("5/10/2012");
            masa.setTime("1:00 pm");
            lunchInvites.add(tbell);
            lunchInvites.add(cosi);
            lunchInvites.add(masa);
            
            Lunch dhaba = new Lunch("Desi Dhaba");
            dhaba.setDate("5/7/2012");
            dhaba.setTime("1:00 pm");
            
            Lunch maggianos = new Lunch("Maggiano's");
            maggianos.setDate("5/9/2012");
            maggianos.setTime("1:00 pm");
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
	
	public void setClickedLunch(Lunch l)
	{
		clickedLunch = l;
	}
	
	public Lunch getCreatingLunch() {
	    return this.currentCreatingLunch;
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
	
}
