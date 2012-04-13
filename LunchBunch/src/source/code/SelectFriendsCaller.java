package source.code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SelectFriendsCaller extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_caller);
    
    }
    
    public void onButtonClicked(View v) {
        // Do something when the button is clicked
        switch(v.getId()) {
        case R.id.gotoselectfriends:
            Intent selectFriendsIntent = new Intent(this, SelectFriends.class);
            startActivity(selectFriendsIntent);
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

}
