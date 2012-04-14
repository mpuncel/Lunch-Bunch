package source.code;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class BrowseInvites extends Activity {

    ArrayList<Lunch> lunches;
    Global state;
    ListView lv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_page);
        
        state = (Global) getApplication();
        state.makeLunches();
        lunches = state.getLunchInvites();
        lv = (ListView) findViewById(R.id.listEntries);
        lv.setTextFilterEnabled(true);

        ArrayAdapter<Lunch> arrayAdapter = new ArrayAdapter<Lunch>(this, R.layout.list_item, lunches);
        arrayAdapter.setNotifyOnChange(true);
  	  	lv.setAdapter(new ArrayAdapter<Lunch>(this, R.layout.list_item, lunches));
        //Toast.makeText(BrowseInvites.this, "OnCreateMethod", Toast.LENGTH_SHORT).show();


      	lv.setOnItemClickListener(new OnItemClickListener() {
      	    public void onItemClick(AdapterView<?> parent, View view,
      	        int position, long id) {
      	        Global state = (Global)getApplication();
      	        Intent eventDetails = new Intent(parent.getContext(), InviteDetails.class);
      	        Lunch clickedLunch = state.getLunchInvite(position);
      	        //System.out.println("Browse Invites " + clickedLunch.getTitle() + clickedLunch.getDate() + clickedLunch.getTime());
      	        //eventDetails.putExtra("lunch", state.getLunchInvite(position));
      	        //eventDetails.putExtra("position", position);
      	        state.setCurrentClickedLunch(clickedLunch);
      	        startActivity(eventDetails);
      	        finish();
      	      
      	    }
      	});
  	  
  	}
    
    @Override
    protected void onNewIntent(Intent intent) {
        Toast.makeText(BrowseInvites.this, "" + intent.getClass(), Toast.LENGTH_SHORT).show();

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Toast.makeText(BrowseInvites.this, "" + intent.getClass(), Toast.LENGTH_SHORT).show();

    	
    }
  	
  	public void onButtonClicked(View v) {
          // Do something when the button is clicked
  		  Button b = (Button) v;
          //Toast.makeText(BrowseInvites.this, "" + b.getText(), Toast.LENGTH_SHORT).show();
          if (b.getText().equals("Lunches I'm Attending"))
          {
        	  Intent intent = new Intent(this, BrowseAttending.class);
              startActivity(intent);      
          }
          if (b.getText().equals("Create"))
          {
        	  Intent intent = new Intent(this, CreateNewLunch.class);
              startActivity(intent);        	  
          }
          finish();
      }
}
