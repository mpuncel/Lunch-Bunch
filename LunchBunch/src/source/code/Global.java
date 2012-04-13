package source.code;

import java.util.ArrayList;

import android.app.Application;

public class Global extends Application 
{
	private ArrayList<Lunch> lunchInvites;
	private ArrayList<Lunch> lunchesAttending;
	
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
	
	public ArrayList<Lunch> getLunchesAttending()
	{
		return lunchesAttending;
	}
	
}
