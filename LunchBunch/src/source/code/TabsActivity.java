package source.code;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TabsActivity extends Activity implements OnTabChangeListener {
	TabHost tabs;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        tabs = (TabHost)findViewById(R.id.tabhost);

        tabs.setup();


        TabHost.TabSpec spec1 = tabs.newTabSpec("Create");//.setContent(intent);


        spec1.setContent(R.id.AnalogClock01);
        spec1.setIndicator("Create");
        
        tabs.addTab(spec1);

        TabHost.TabSpec spec2 = tabs.newTabSpec("tag2");
        spec2.setContent(R.id.DigitalClock01);
        spec2.setIndicator("Digital Clock");
        

        tabs.addTab(spec2);
        
        int numtabs = tabs.getTabWidget().getChildCount();
        for(int i=0; i<numtabs; i++)
        {
            View tab = tabs.getTabWidget().getChildAt(i);
            tab.setContentDescription("tab" + i);
	        /*tab.setOnClickListener(new OnClickListener() 
	        {
		  		public void onClick(View v) 
		  		{
		        Toast.makeText(TabsActivity.this, v.getContentDescription() + " clicked", Toast.LENGTH_SHORT).show();
		        if (v.getContentDescription() == "tab0")
		        {
		        	
		        }
				
		  		}
	        });*/
	    }
        
        tabs.setOnTabChangedListener(this);
    }
    
    public void onTabChanged(String tabId) 
    {
    	Toast.makeText(TabsActivity.this, tabId, Toast.LENGTH_SHORT).show();
    	if (tabId.equals("Create"))
    	{
    		Intent intent = new Intent(this, CreateNewLunch.class);
    		startActivity(intent);
    	}
    }
        
	  
    
    public void onTabClicked(View v)
    {
    	int numtabs = tabs.getTabWidget().getChildCount();
        for(int i=0; i<numtabs; i++)
        {
           
	        
		        Toast.makeText(TabsActivity.this, v.getContentDescription(), Toast.LENGTH_SHORT).show();
		        if (v.getContentDescription() == "tab0")
		        {
		        	Intent intent = new Intent(this, LunchInvites.class);
		        	startActivity(intent);
		        }
			
	    }
    	
    }
}
