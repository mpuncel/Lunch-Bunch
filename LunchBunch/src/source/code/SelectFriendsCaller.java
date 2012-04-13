package source.code;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SelectFriendsCaller extends Activity {

    private final static int SELECTFRIENDSID = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_caller);
        Global state = (Global) getApplication();
        ArrayList<Lunch> lunchInvites = makeLunches();
        state.setLunchInvites(lunchInvites);
        state.setLunchesAttending(lunchInvites); // set lunches attending to same as lunch invites for now
    }
    
    public void onButtonClicked(View v) {
        // Do something when the button is clicked
        switch(v.getId()) {
        case R.id.gotoselectfriends:
            Intent selectFriendsIntent = new Intent(this, SelectFriends.class);
            startActivityForResult(selectFriendsIntent, SELECTFRIENDSID);
            break;
            
        case R.id.gotolunchinvites:
            Intent lunchInvitesIntent = new Intent(this, BrowseInvites.class);
            lunchInvitesIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(lunchInvitesIntent);
            break;
            
        case R.id.gotocreatenewlunch:
            Intent createNewLunchIntent = new Intent(this, CreateNewLunch.class);
            startActivity(createNewLunchIntent);
            break;
            
        default:
            Toast.makeText(SelectFriendsCaller.this, "Unknown ID", Toast.LENGTH_SHORT).show();
            throw new RuntimeException("Unknown button ID");
        }
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            throw new RuntimeException("Result Code not okay");
        }
        
        else {
            switch(requestCode) {
            case SELECTFRIENDSID:
                ArrayList<Friend> selectedFriends = data.getParcelableArrayListExtra("friend names");
                Toast.makeText(SelectFriendsCaller.this, String.valueOf(selectedFriends.size()), Toast.LENGTH_SHORT).show();
                
                break;
            default:
                throw new RuntimeException("Request Code not Recognized");
            }
        }
    }
    
    public ArrayList<Lunch> makeLunches()
  	{
    	ArrayList<Lunch> lunches = new ArrayList<Lunch>();
  		lunches.add(new Lunch("Taco Bell"));
  		lunches.add(new Lunch("Cosi"));
  		lunches.add(new Lunch("Masa"));
  		lunches.add(new Lunch("Taco Bell"));
  		lunches.add(new Lunch("Cosi"));
  		lunches.add(new Lunch("Masa"));
  		lunches.add(new Lunch("Taco Bell"));
  		lunches.add(new Lunch("Cosi"));
  		lunches.add(new Lunch("Masa"));
  		return lunches;

  	}

}
