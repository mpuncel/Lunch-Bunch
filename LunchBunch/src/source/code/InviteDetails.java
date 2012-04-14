package source.code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InviteDetails extends Activity {

    private Lunch thisLunch;
    private int thisPosition;
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //TODO: fix layout (accept and decline buttons not aligned)
	    setContentView(R.layout.eventdetails);
	
	    Intent data = getIntent();
	    thisLunch = data.getParcelableExtra("lunch");
	    //TODO: rewrite xml file and this class so that it displays data from thisLunch. Should
	    //also show list of friends
	    thisPosition = data.getIntExtra("position", -1);
	}
	
	public void onButtonClicked(View v) {
	    Global state = (Global)getApplication();
	    Intent invites = new Intent(this, BrowseInvites.class);
	    switch(v.getId()) {
	    case R.id.accept:
	        state.addLunchAttending(thisLunch);
	        state.removeLunchInvite(thisPosition);
	        startActivity(invites);
	        finish();
	        break;
	    case R.id.decline:
	        state.removeLunchInvite(thisPosition);
	        startActivity(invites);
	        finish();
	        break;
	    default:
	        throw new RuntimeException("Button ID not understood");
	    }
	}

}
