package source.code;

import android.util.Log;

public class Lunch 
{
	
	private String name;

	public Lunch(String n)
	{
		name = n;
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
