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
import android.widget.TextView;
import android.widget.Toast;

public class BrowseAttending extends Activity {

    ArrayList<Lunch> lunches;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attending_page);

        Global state = (Global) getApplication();
        lunches = state.getLunchesAttending();
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
          //Toast.makeText(BrowseAttending.this, "" + b.getText(), Toast.LENGTH_SHORT).show();
          
  		  switch(v.getId()) {
  		  case R.id.create:
              Intent create = new Intent(this, CreateNewLunch.class);
              startActivity(create);
              break;
  		  case R.id.lunchinvitestab:
  		    Intent lunchinvites = new Intent(this, BrowseInvites.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(lunchinvites); 
            break;
          default:
              throw new RuntimeException("Button ID unknown");
  		  }

      }

  	
  	public void makeLunches()
  	{
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));
  		lunches.add(new Lunch("Attending"));

  	}
}
