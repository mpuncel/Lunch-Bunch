package source.code;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectFriends extends Activity {

    ArrayList<Friend> friends;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectfriends);
        
        friends = new ArrayList<Friend>();
        makeFriends();

        ListView lv = (ListView) findViewById(R.id.listEntries);
        lv.setTextFilterEnabled(true);

        lv.setAdapter(new ArrayAdapter<Friend>(this, R.layout.friend_item, friends));
        

        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
            // When clicked, show a toast with the TextView text
              CheckedTextView textView = (CheckedTextView)view;
              textView.setChecked(!textView.isChecked());
              Toast.makeText(getApplicationContext(), String.valueOf(((CheckedTextView) view).isChecked()),
                      Toast.LENGTH_SHORT).show();

          }
        });
        

    }
    
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

}
