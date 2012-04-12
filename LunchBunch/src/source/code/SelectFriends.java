package source.code;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
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
        setContentView(R.layout.selectfriends);
        
        friends = new ArrayList<Friend>();
        makeFriends();

        ListView lv = (ListView) findViewById(R.id.listEntries);
        lv.setTextFilterEnabled(true);

        this.friendListAdapter = new FriendListAdapter<Friend>(this, R.layout.friend_item, friends);
        lv.setAdapter(this.friendListAdapter);
        

        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
              Toast.makeText(getApplicationContext(), String.valueOf(((CheckedTextView) view).getText()),
                      Toast.LENGTH_SHORT).show();
            // When clicked, show a toast with the TextView text
              CheckedTextView textView = (CheckedTextView)view;
              textView.setChecked(!textView.isChecked());
              //this.friendListAdapter.setChecked(position, !textView.isChecked());
              


          }
        });
        

    }
    
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        CheckedTextView cv = (CheckedTextView)v;
//        this.friendListAdapter.setChecked(position, !cv.isChecked());
//        cv.setChecked(!cv.isChecked());
//    }
    
    public void onSelectFriendsButtonClicked() {
        
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
    
    private class FriendListAdapter<T> extends ArrayAdapter {
        
        private ArrayList<Boolean> isChecked;
        public FriendListAdapter(Context context, int resource, ArrayList<Friend> friends)
        {
            super(context, resource, friends);
            isChecked = new ArrayList<Boolean>(friends.size());
            for (int i = 0; i < friends.size(); i++) {
                isChecked.add(i, false);
            }
        }


        /**
         * Make a SpeechView to hold each row.
         * @see android.widget.ListAdapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            CheckedTextView v = (CheckedTextView)super.getView(position, convertView, parent);
            v.setChecked(isChecked.get(position));
            return v;
        }
        
        public void setChecked(int position, boolean checked) {
            isChecked.add(position, checked);
        }
    }


}
