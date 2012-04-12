package source.code;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class LunchInvites extends Activity {

	private ArrayList<Lunch> lunches;


	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
      setContentView(R.layout.list_page);

	  lunches = new ArrayList<Lunch>();
	  makeLunches();

      ListView lv = (ListView) findViewById(R.id.listEntries);
	  lv.setTextFilterEnabled(true);
	  
	  lv.setAdapter(new ArrayAdapter<Lunch>(this, R.layout.list_item, lunches));
	  
	  
	  lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	      // When clicked, show a toast with the TextView text
	      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
	          Toast.LENGTH_SHORT).show();
	    }
	  });
	}
	
	public void onButtonClicked(View v) {
        // Do something when the button is clicked
        Toast.makeText(LunchInvites.this, "Go to Create New Lunch", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CreateNewLunch.class);
        startActivity(intent);
    }

	
	public void makeLunches()
	{
		lunches.add(new Lunch("Taco Bell"));
		lunches.add(new Lunch("Cosi"));
		lunches.add(new Lunch("Masa"));
		lunches.add(new Lunch("Taco Bell"));
		lunches.add(new Lunch("Cosi"));
		lunches.add(new Lunch("Masa"));
		lunches.add(new Lunch("Taco Bell"));
		lunches.add(new Lunch("Cosi"));
		lunches.add(new Lunch("Masa"));
		lunches.add(new Lunch("Taco Bell"));
		lunches.add(new Lunch("Cosi"));
		lunches.add(new Lunch("Masa"));
		lunches.add(new Lunch("Taco Bell"));
		lunches.add(new Lunch("Cosi"));
		lunches.add(new Lunch("Masa"));
		lunches.add(new Lunch("Taco Bell"));
		lunches.add(new Lunch("Cosi"));
		lunches.add(new Lunch("Masa"));

	}
	
	
			
}
