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
        setContentView(R.layout.selectfriends);
        
        friends = new ArrayList<Friend>();
        makeFriends();

        ListView lv = (ListView) findViewById(R.id.listEntries);
        lv.setTextFilterEnabled(true);

        this.friendListAdapter = new FriendListAdapter<Friend>(this, R.layout.friend_item, friends);
        lv.setAdapter(this.friendListAdapter);
        

        lv.setOnItemClickListener(new MyOnItemClickListener(this.friendListAdapter));

    }
    
    public void onSelectFriendsButtonClicked() {
        Intent selectedFriendData = new Intent();
        selectedFriendData.putParcelableArrayListExtra("friend names", this.friendListAdapter.getSelectedFriends());
        setResult(Activity.RESULT_OK, selectedFriendData);
        finish();
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
                Toast.makeText(getApplicationContext(), String.valueOf(((CheckedTextView) view).getText()),
                        Toast.LENGTH_SHORT).show();
              // When clicked, show a toast with the TextView text
                CheckedTextView textView = (CheckedTextView)view;
                textView.setChecked(!textView.isChecked());
                this.friendListAdapter.setChecked(position, textView.isChecked());
                


        }
    }
    
    private class FriendListAdapter<T> extends ArrayAdapter {
        
        private ArrayList<Boolean> isChecked;
        private Context context;
        private ArrayList<Friend> friends;
        public FriendListAdapter(Context context, int resource, ArrayList<Friend> friends)
        {
            super(context, resource, friends);
            this.context = context;
            this.friends = friends;
            isChecked = new ArrayList<Boolean>(friends.size());
            for (int i = 0; i < friends.size(); i++) {
                isChecked.add(i, false);
            }
        }
        
        public View getView(int position, View convertView, ViewGroup parent) {
            CheckedTextView v = (CheckedTextView)super.getView(position, convertView, parent);
            v.setChecked(isChecked.get(position));
            
            return v;
        }
        
        public void setChecked(int position, boolean checked) {
            isChecked.add(position, checked);
        }
        
        public ArrayList<Friend> getSelectedFriends() {
            ArrayList<Friend> selectedFriends = new ArrayList<Friend>();
            for (int i = 0; i < this.friends.size(); i++) {
                if (this.isChecked.get(i) == true) {
                    selectedFriends.add(this.friends.get(i));
                }
            }
            return selectedFriends;
        }
    }


}
