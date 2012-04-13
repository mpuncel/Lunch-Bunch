package source.code;

import java.util.ArrayList;

public class Lunch 
{
	
	private String name;
	private ArrayList<Friend> friends;

	public Lunch(String n)
	{
		name = n;
	}
	
	public void setFriends(ArrayList<Friend> friends) {
	    this.friends = friends;
	}
	
	public String getTitle()
	{
		return name;
	}
	
	public String toString()
	{
		
		Object a[] = { name, "Sat. 4/7/2012" ,
		        "12:00 pm"};

		String s = String.format("%1$-10s %n %3$-8s    %2$s", a);
		//System.out.println(s);
		return s;
	}

}
