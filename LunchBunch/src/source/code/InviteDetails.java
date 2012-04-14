package source.code;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InviteDetails extends Activity {

    private Lunch thisLunch;
    private int thisPosition;
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //TODO: fix layout (accept and decline buttons not aligned)
	    setContentView(R.layout.eventdetails);
	
	    Global state = (Global) getApplication();
	    //Intent data = getIntent();
	    thisLunch = state.getCurrentClickedLunch();
	    //System.out.println(thisLunch==null);
	    //TODO: rewrite xml file and this class so that it displays data from thisLunch. Should
	    //also show list of friends
	    TextView location = (TextView) findViewById(R.id.location);
	    location.setText(thisLunch.getTitle());
	    
	    TextView date = (TextView) findViewById(R.id.date);
	    date.setText(thisLunch.getDate());
	    
	    TextView time = (TextView) findViewById(R.id.time);
	    time.setText(thisLunch.getTime());
	    
	    ListView attending = (ListView) findViewById(R.id.listfriends);
	    ArrayList<Friend> friends = thisLunch.getFriends();
	    attending.setAdapter(new ArrayAdapter<Friend>(this, R.layout.attending_item, friends));
	    //thisPosition = data.getIntExtra("position", -1);
	}
	
	public void onButtonClicked(View v) {
	    Global state = (Global)getApplication();
	    Intent invites = new Intent(this, BrowseInvites.class);
	    switch(v.getId()) {
	    case R.id.accept:
	        state.addLunchAttending(thisLunch);
	        state.removeLunchInvite(thisLunch.getTitle());
	        startActivity(invites);
	        Toast.makeText(getApplicationContext(), thisLunch.getTitle() + " added to Lunches I'm Attending", Toast.LENGTH_SHORT).show();
	        finish();
	        break;
	    case R.id.decline:
	        state.removeLunchInvite(thisLunch.getTitle());
	        startActivity(invites);
	        finish();
	        break;
	    default:
	        throw new RuntimeException("Button ID not understood");
	    }
	}

}
