package source.code;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

public class SelectFriends extends Activity {

    ArrayList<Friend> friends;
    FriendListAdapter<Friend> friendListAdapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(Activity.RESULT_CANCELED);
        setContentView(R.layout.selectfriends);
        
        friends = new ArrayList<Friend>();
        makeFriends();

        ListView lv = (ListView) findViewById(R.id.listEntries);
        lv.setTextFilterEnabled(true);

        Global state = (Global) getApplication();
        if (state.getFriendListAdapter() == null) {
            this.friendListAdapter = new FriendListAdapter<Friend>(this, R.layout.friend_item, friends);
            state.setFriendListAdapter(this.friendListAdapter);
        }
        
        else {
            this.friendListAdapter = state.getFriendListAdapter();
        }
        lv.setAdapter(this.friendListAdapter);
        
        
        lv.setOnItemClickListener(new MyOnItemClickListener(this.friendListAdapter));

    }
    
    public void onSelectFriendsButtonClicked(View v) {
        if (this.friendListAdapter.getNumChecked() > 0) {
            Global state = (Global) getApplication();
            ArrayList<Friend> friends = this.friendListAdapter.getSelectedFriends();
            state.getCreatingLunch().setFriends(friends);
            state.createLunchDone();
            Intent attending = new Intent(this, BrowseAttending.class);
            startActivity(attending);
            setResult(Activity.RESULT_OK);
            state.setFriendListAdapter(null);
            finish();
        }
        else {
            Toast.makeText(this, "Please invite at least one friend to this lunch!", Toast.LENGTH_LONG).show();
        }
    }
    
    public void makeFriends()
    {
        String[] names = {"Albert Kao", 
                "Alejandro Dos Reis",
                "Ambar Mehta",
                "Anjali Muralidhar", 
                "Ashwin Suresh",
                "Carter Chang",
                "David Way",
                "Dhruv Garg",
                "Dhruv Parthasarathy",
                "Jay Park",
                "Jerry Wang",
                "Kavita Chandra",
                "Mathura Sridharan",
                "Matthew Barron",
                "Michael Puncel",
                "Nischay Kumar",
                "Pallavi Powale", 
                "Rob Miller", 
                "Samvaran Sharma", 
                "Sashko Stubailo",
                "Stephen Ge",
                "Ted Benson", 
                "Timmy Galvin", 
                "Yuchen Feng",
                "Zach Steward"};
        for (String name : names) {
            friends.add(new Friend(name));
        }

    }
    private class MyOnItemClickListener implements OnItemClickListener {
        private FriendListAdapter<Friend> friendListAdapter;
        public MyOnItemClickListener(FriendListAdapter<Friend> friendListAdapter) {
            this.friendListAdapter = friendListAdapter;
        }
        
        public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
              // When clicked, show a toast with the TextView text
                CheckedTextView textView = (CheckedTextView)view;
                textView.setChecked(!textView.isChecked());
                this.friendListAdapter.setChecked(position, textView.isChecked());
                


        }
    }

}
